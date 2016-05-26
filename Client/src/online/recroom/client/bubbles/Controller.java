package online.recroom.client.bubbles;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import online.recroom.client.Animator;

import java.io.IOException;
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

    public void setEndpoint(Endpoint endpoint)
    {
        this.endpoint =endpoint;
    }

    public void initialize()
    {
        setUpConsoleAutoResize();
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
}
