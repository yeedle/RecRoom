package online.recroom.client;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Created by Yeedle on 5/16/2016 9:20 PM.
 */
public class WelcomeController
{
    @FXML
    Button chessBtn;
    @FXML
    Button checkersBtn;
    @FXML
    Button tictactoeBtn;
    @FXML
    Button bubblesBtn;
    @FXML
    Button pongBtn;

    @FXML
    private void handleButtonAction(ActionEvent event) throws IOException
    {
        Stage stage;
        Parent root;
            //get reference to the button's stage
            stage=(Stage) ((Node)event.getSource()).getScene().getWindow();
            //load up OTHER FXML document
            root = FXMLLoader.load(getClass().getResource("fxml/GameIntro.fxml"));

        //create a new scene with root and set the stage
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }


}
