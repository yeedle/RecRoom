package online.recroom.client.bubbles;

import javafx.animation.KeyFrame;
import javafx.animation.ScaleTransition;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;

import javax.websocket.Session;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by Yeedle on 5/25/2016 12:54 AM.
 */
public class BubblesController
{
    @FXML
    Group bubblePane;
    Session session;
    ConcurrentHashMap<Long, Bubble> bubbleMap = new ConcurrentHashMap<>();
    File popSoundFile =  new File("Client/src/online/recroom/client/assets/pop.mp3");
    Media popSound = new Media(popSoundFile.toURI().toString());
    Timeline t = new Timeline();
    private final double SPEED = 100;

    public void setSession(Session session)
    {
        this.session =session;
    }

    public void gameStarted(Bubble[] bubbles)
    {
        for (Bubble bubble : bubbles)
        {
            t.getKeyFrames().add(new KeyFrame(Duration.millis(SPEED), e -> bubble.move()));
            bubbleMap.put(bubble.id, bubble);
            long id = bubble.id;
            bubble.setOnMouseClicked(e -> sendMessage(id));
            Platform.runLater(() -> bubblePane.getChildren().add(bubble));
        }

        t.setCycleCount(Timeline.INDEFINITE);
        t.play();


    }

    public void sendMessage(final Long id)
    {
        try
        {
            session.getBasicRemote().sendText(Long.toString(id));
        } catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public void bubblePopped(long poppedBubbleId)
    {
        Bubble bubble = bubbleMap.get(poppedBubbleId);
        ScaleTransition st = new ScaleTransition(Duration.millis(100), bubble);
        final int  POPPING_SIZE = 5;
        st.setByX(POPPING_SIZE);
        st.setByY(POPPING_SIZE);
        st.setOnFinished(e -> {bubblePane.getChildren().remove(bubble); new MediaPlayer(popSound).play();});
        st.play();
        bubbleMap.remove(poppedBubbleId);
    }

}
