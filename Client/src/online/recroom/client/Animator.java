package online.recroom.client;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.util.Duration;

/**
 * Created by Yeedle on 5/25/2016 10:37 AM.
 */
public class Animator
{

    private static Timeline timeline = new Timeline();
    final static double TEXT_TYPING_SPEED = 90;

    public static void runningText(String str, VBox vbox)
    {
        Text text = new Text();
        text.getStyleClass().add("console-text");
        text.setWrappingWidth(500);
        Platform.runLater(() -> vbox.getChildren().add(text));

        final IntegerProperty i = new SimpleIntegerProperty(0);

        timeline = new Timeline();

        KeyFrame keyFrame = new KeyFrame(
                Duration.millis(TEXT_TYPING_SPEED),
                event -> typingKeyFrameEvent(str, text, i));
        timeline.getKeyFrames().add(keyFrame);
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
    }

    private static void typingKeyFrameEvent(String str, Text text, IntegerProperty i)
    {
        if (i.get() > str.length()) {
            timeline.stop();
        } else {
            text.setText(str.substring(0, i.get()));
            i.set(i.get() + 1);
        }
    }
}
