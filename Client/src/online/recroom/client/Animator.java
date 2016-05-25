package online.recroom.client;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.ScaleTransition;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.text.Text;
import javafx.util.Duration;
import online.recroom.client.bubbles.Bubble;

import java.io.File;
import java.sql.Time;

/**
 * Created by Yeedle on 5/25/2016 10:37 AM.
 */
public class Animator
{

    final static double TEXT_TYPING_SPEED = 90;
    static File popSoundFile =  new File("Client/src/online/recroom/client/assets/pop.mp3");
    static Media popSound = new Media(popSoundFile.toURI().toString());

    public static void runningText(String str, VBox vbox)
    {
        Text text = new Text();
        text.getStyleClass().add("console-text");
        text.setWrappingWidth(500);
        Platform.runLater(() -> vbox.getChildren().add(text));

        final IntegerProperty i = new SimpleIntegerProperty(0);

        Timeline timeline = new Timeline();

        KeyFrame keyFrame = new KeyFrame(
                Duration.millis(TEXT_TYPING_SPEED),
                event -> typingKeyFrameEvent(str, text, i, timeline));
        timeline.getKeyFrames().add(keyFrame);
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
    }

    private static void typingKeyFrameEvent(String str, Text text, IntegerProperty i, Timeline timeline)
    {
        if (i.get() > str.length()) {
            timeline.stop();
        } else {
            text.setText(str.substring(0, i.get()));
            i.set(i.get() + 1);
        }
    }

    public static void animateBubblePopping(long poppedBubbleId, Bubble bubble, Pane bubblePane)
    {
        ScaleTransition st = new ScaleTransition(Duration.millis(100), bubble);
        final int  POPPING_SIZE = 5;
        st.setByX(POPPING_SIZE);
        st.setByY(POPPING_SIZE);
        st.setOnFinished(e -> {bubblePane.getChildren().remove(bubble); new MediaPlayer(popSound).play();});
        st.play();

    }
}
