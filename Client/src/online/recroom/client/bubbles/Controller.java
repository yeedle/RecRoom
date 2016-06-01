package online.recroom.client.bubbles;

import com.google.gson.Gson;
import javafx.application.Platform;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.Window;
import online.recroom.client.Animator;
import online.recroom.client.Scener;
import online.recroom.messages.*;
import online.recroom.messages.Message;
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
    Scene root;
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
       bubblePane.sceneProperty().addListener((observableScene, oldScene, newScene) -> attachKeyListners(oldScene, newScene));
    }

    private void attachKeyListners(Scene oldScene, Scene newScene)
    {
        if (oldScene == null && newScene != null)
            newScene.setOnKeyPressed(e -> handleKeyStrokes(e));

    }

    private void setUpConsoleAutoResize()
    {
        console.setVvalue(1.0);
        vbox.heightProperty().addListener((observable, oldValue, newValue) -> console.setVvalue(newValue.doubleValue()));
    }

    public void gameStarted(Bubble[] bubbles)
    {
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
            bubble.setOnMouseClicked(e -> sendPoppedBubbleID(bubble.id));
        }
        Platform.runLater(() -> bubblePane.getChildren().addAll(bubbles));
    }

    private void sendPoppedBubbleID(Long id)
    {
        try
        {
            endpoint.sendMessage(id);
        } catch (IOException exception)
        {
            exception.printStackTrace();
        }
    }
    private void sendPoppedBubbleID(BubblePoppedMessage msg) throws EncodeException
    {
        Gson gson = new Gson();
        String json = gson.toJson(msg);
        Message message = new Message(msg.getClass(), json);
        try
        {
            endpoint.sendMessage(message);
        } catch (IOException exception)
        {
            exception.printStackTrace();
        }
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
        console("Waiting for another player to join...");
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


    public <T> void handleMessage(T message)
    {
        System.out.println(message.getClass());
        if (message instanceof GamePending)
        console("Waiting for another player to join...");
        else if (message instanceof GameStarted)
            handleMessage((GameStarted)message);
        else if (message instanceof BubblePoppedMessage)
            bubblePopped(((BubblePoppedMessage) message).poppedBubbleId);
        else if(message instanceof PlayerJoined)
            console(((PlayerJoined) message).player.name + " joined!");
        else if (message instanceof PlayerLeft)
            console(((PlayerLeft) message).player.name + " couldn't take the heat.");
        else if (message instanceof  GameOver)
                gameOver(((GameOver) message).winner.name, ((GameOver) message).score);
        else
            console("I'm getting some weird binary :/");
    }



    public void handleMessage(GameStarted message)
    {
        Bubble[] bubbles = new Bubble[message.bubbles.length];
        for (int i = 0; i < message.bubbles.length ; i++)
           bubbles[i] = new Bubble(message.bubbles[i]);
        // todo print players and join/start status to console
        console("Waiting for another player to join...");
        console("Go!");
        addBubblesToPane(bubbles);
    }

    public void handleKeyStrokes(KeyEvent event)
    {

            final KeyCodeCombination kc = new KeyCodeCombination(KeyCode.B, KeyCombination.SHIFT_ANY, KeyCombination.CONTROL_ANY);
            if (kc.match(event))
            {
                final int MAGIC_NUMBER = 20;
                int i = 0;
                for (Enumeration<Long> bubbles = bubbleMap.keys(); i < MAGIC_NUMBER && bubbles.hasMoreElements(); i++)
                    sendPoppedBubbleID(bubbles.nextElement());
            }
            if (event.getCode().equals(KeyCode.BACK_SPACE))
            {
                endpoint.closeConnection(CloseReason.CloseCodes.GOING_AWAY);
            }
    }
}
