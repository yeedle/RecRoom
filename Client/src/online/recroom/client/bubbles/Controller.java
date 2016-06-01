package online.recroom.client.bubbles;

import com.google.gson.Gson;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import online.recroom.client.Animator;
import online.recroom.messages.Message;
import online.recroom.messages.bubble.enums.BubbleMessages;
import online.recroom.messages.bubble.messages.*;

import javax.websocket.CloseReason;
import javax.websocket.EncodeException;
import java.io.IOException;
import java.util.Enumeration;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by Yeedle on 5/25/2016 12:54 AM.
 */
public class Controller
{
    @FXML Pane bubblePane;
    @FXML ScrollPane console;
    @FXML VBox vbox;
    Endpoint endpoint;
    ConcurrentHashMap<Long, Bubble> bubbleMap = new ConcurrentHashMap<>();

    public Stage getStage(){
       return (Stage)bubblePane.getScene().getWindow();
    }

    public void setEndpoint(Endpoint endpoint)
    {
        this.endpoint =endpoint;
    }

    public void initialize()
    {
        setUpConsoleAutoResize();
       bubblePane.sceneProperty().addListener((observableScene, oldScene, newScene) -> attachKeyListeners(oldScene, newScene));
    }

    private void attachKeyListeners(Scene oldScene, Scene newScene)
    {
        if (oldScene == null && newScene != null)
            newScene.setOnKeyPressed(e -> handleKeyPressed(e));

    }

    private void handleKeyPressed(KeyEvent e)
    {
        try
        {
            handleKeyStrokes(e);
        } catch (EncodeException e1)
        {
            e1.printStackTrace();
        }
    }

    private void setUpConsoleAutoResize()
    {
        console.setVvalue(1.0);
        vbox.heightProperty().addListener((observable, oldValue, newValue) -> console.setVvalue(newValue.doubleValue()));
    }

    public void gameStarted(Bubble[] bubbles, Player[] players)
    {
        if (players.length == 2)
            console(players[0].name + " v. " + players[1].name);
        console("Go!");
        addBubblesToPane(bubbles);
    }

    public void gameJoined(Bubble[] bubbles, Player[] players)
    {
        String str = getStringOf(players);
        console("You joined a game with " + str);
        addBubblesToPane(bubbles);
    }

    private void addBubblesToPane(Bubble[] bubbles)
    {
        for (Bubble bubble : bubbles)
        {
            Animator.setAnimationFor(bubble);
            bubbleMap.put(bubble.id, bubble);
            bubble.setOnMouseClicked(e -> notifyServer(bubble.id));
        }
        Platform.runLater(() -> bubblePane.getChildren().addAll(bubbles));
    }

    private void notifyServer(Long bubbleId)
    {
        try { sendPoppedBubbleID(new BubblePoppedMessage(bubbleId));}
        catch (EncodeException e) {e.printStackTrace();}
    }

    private void sendPoppedBubbleID(BubblePoppedMessage msg) throws EncodeException
    {
        String json = new Gson().toJson(msg);
        Message message = new Message(BubbleMessages.BUBBLE_POPPED, json);
        try { endpoint.sendMessage(message);}
        catch (IOException exception) { exception.printStackTrace();}
    }

    public void bubblePopped(long poppedBubbleId)
    {
        Animator.animateBubblePopping(bubbleMap.remove(poppedBubbleId), bubblePane);
    }

    public void console(String str)
    {
        Animator.runningText(str, vbox);
    }

    public void gamePending()
    {
        console("Waiting for another user to join...");
    }


    private String getStringOf(Player[] players)
    {
        String str = "";
        for (int i = 0; i < players.length-1; i++)
            str += players[i].name + ", ";
        str += "and " + players[players.length-1].name +".";
        return str;
    }

    public void gameOver(String winner, int score)
    {
        console(winner + " can click the fastest, and won with score of " + score);
    }

    public void error(Throwable t)
    {
        console(t.getMessage() + "! Sorry 'bout that :(");
    }

    public void handleMessage(GameStarted message)
    {
        Bubble[] bubbles = new Bubble[message.bubbles.length];
        for (int i = 0; i < message.bubbles.length ; i++)
           bubbles[i] = new Bubble(message.bubbles[i]);
        console("Waiting for another player to join...");
        console("Go!");
        addBubblesToPane(bubbles);
    }

    public void handleMessage(GamePending message)
    {
        console("Waiting for another player to join...");
    }

    public void handleMessage(PlayerJoined message)
    {
        console(message.player.name + " joined!");
    }
    public void handleMessage(PlayerLeft message)
    {
        console( message.player.name + " couldn't take the heat.");
    }

    public void handleMessage(GameOver message)
    {
        gameOver( message.winner.name,  message.score);
    }
    public void handleMessage(BubblePoppedMessage message)
    {
        bubblePopped(message.poppedBubbleId);
    }


    public void handleKeyStrokes(KeyEvent event) throws EncodeException
    {
        final KeyCodeCombination kc = new KeyCodeCombination(KeyCode.B, KeyCombination.SHIFT_ANY, KeyCombination.CONTROL_ANY);
        if (kc.match(event))
        {
            popManyBubblesAtOnce(20);
        }
        if (event.getCode().equals(KeyCode.BACK_SPACE))
        {
            endpoint.closeConnection(CloseReason.CloseCodes.GOING_AWAY);
        }

    }

    private void popManyBubblesAtOnce(final int MAGIC_NUMBER) throws EncodeException
    {
        int i = 0;
        for (Enumeration<Long> bubbles = bubbleMap.keys(); i < MAGIC_NUMBER && bubbles.hasMoreElements(); i++)
        {
            BubblePoppedMessage msg = new BubblePoppedMessage(bubbles.nextElement());
            sendPoppedBubbleID(msg);
        }
    }
}
