package online.recroom.client.welcome;

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
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../chess/ChessIntro.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        Stage  stage=(Stage) ((Node)event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }


}
