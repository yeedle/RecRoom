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
    public static void runningText(String str, VBox vbox)
    {
        Text text = new Text();
        text.getStyleClass().add("console-text");
        Platform.runLater(() -> vbox.getChildren().add(text));

        final IntegerProperty i = new SimpleIntegerProperty(0);

        Timeline timeline = new Timeline();
        KeyFrame keyFrame = new KeyFrame(
                Duration.millis(90),
                event -> {
                    if (i.get() > str.length()) {
                        timeline.stop();
                    } else {
                        text.setText(str.substring(0, i.get()));
                        i.set(i.get() + 1);
                    }
                });
        timeline.getKeyFrames().add(keyFrame);
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
    }
}
