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

/**
 * Created by Yeedle on 5/25/2016 10:37 AM.
 */
public class Animator
{


    private static File popSoundFile = new File("Client/src/online/recroom/client/assets/pop.mp3");
    private static Media popSound = new Media(popSoundFile.toURI().toString());
    private final static double TEXT_TYPING_SPEED = 90;
    private static Duration textTypingDuration = Duration.millis(TEXT_TYPING_SPEED);
    private static final int WRAPPING_WIDTH = 500;
    private static final int BUBBLE_POPPING_DURATION = 100;
    private static final int POPPING_SIZE = 5;
    private static final double BUBBLE_SPEED = 100;

    public static void runningText(String str, VBox vbox)
    {
        Text text = new Text();
        text.getStyleClass().add("console-text");
        text.setWrappingWidth(WRAPPING_WIDTH);
        Platform.runLater(() -> vbox.getChildren().add(text));

        final IntegerProperty i = new SimpleIntegerProperty(0);
        Timeline timeline = new Timeline();
        KeyFrame keyFrame = new KeyFrame(textTypingDuration, e -> typingKeyFrameEvent(str, text, i, timeline));
        timeline.getKeyFrames().add(keyFrame);
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
    }

    private static void typingKeyFrameEvent(String str, Text text, IntegerProperty i, Timeline timeline)
    {
        if (i.get() > str.length())
        {
            timeline.stop();
        } else
        {
            text.setText(str.substring(0, i.get()));
            i.set(i.get() + 1);
        }
    }

    public static void animateBubblePopping(long poppedBubbleId, Bubble bubble, Pane bubblePane)
    {
        ScaleTransition st = new ScaleTransition(Duration.millis(BUBBLE_POPPING_DURATION), bubble);
        st.setByX(POPPING_SIZE);
        st.setByY(POPPING_SIZE);
        st.setOnFinished(e -> {
            bubblePane.getChildren().remove(bubble);
            new MediaPlayer(popSound).play();
        });
        st.play();
    }
    public static void animate(Bubble bubble)
    {
        Timeline t = new Timeline();
        t.getKeyFrames().add(new KeyFrame(Duration.millis(BUBBLE_SPEED), e -> bubble.move()));
        t.setCycleCount(Animation.INDEFINITE);
        t.play();
    }
}
