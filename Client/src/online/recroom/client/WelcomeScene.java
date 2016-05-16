package online.recroom.client;

import javafx.beans.NamedArg;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/**
 * Created by Yeedle on 5/16/2016 3:11 PM.
 */
public class WelcomeScene extends Group
{

    public WelcomeScene()
    {
        Label label = new Label("Welcome to RecRoom");
        VBox vBox = new VBox();
        Button button1 = new Button("Chess");
        Button button2 = new Button("Checkers");
        Button button3 = new Button("Bubbles");
        vBox.getChildren().addAll(label, button1, button2, button3);

        this.getChildren().add(vBox);
    }

}
