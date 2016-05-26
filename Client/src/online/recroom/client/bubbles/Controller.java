package online.recroom.client.bubbles;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import online.recroom.client.Animator;

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

    private void initialize()
    {
        console.setVvalue(1.0);
        vbox.heightProperty().addListener((observable, oldValue, newValue) -> console.setVvalue(newValue.doubleValue()));
    }

    public void gameStarted(Bubble[] bubbles)
    {
        for (Bubble bubble : bubbles)
        {
            Animator.animate(bubble);
            bubbleMap.put(bubble.id, bubble);
            long id = bubble.id;
            bubble.setOnMouseClicked(e -> endpoint.sendMessage(id));
            Platform.runLater(() -> bubblePane.getChildren().add(bubble));
        }
    }

    public void bubblePopped(long poppedBubbleId)
    {
        Bubble bubble = bubbleMap.get(poppedBubbleId);
        Animator.animateBubblePopping(poppedBubbleId, bubble, bubblePane);
        bubbleMap.remove(poppedBubbleId);
    }

    public void console(String str)
    {
        Animator.runningText(str, vbox);
    }
}
