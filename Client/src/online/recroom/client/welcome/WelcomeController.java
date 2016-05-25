package online.recroom.client.welcome;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import online.recroom.client.bubbles.BubblesEndpoint;
import online.recroom.client.bubbles.BubblesController;

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
    TextField usernameTextField;

    @FXML
    private void handleChessButtonAction(ActionEvent event) throws IOException
    {
        System.out.println("chess");
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../chess/Chess.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        Stage  stage=(Stage) ((Node)event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }


    public void handleTicTacToeButtonAction(ActionEvent event) throws IOException
    {
        System.out.println("tic tac toe");
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../ticTacToe/TicTacToe.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        Stage  stage=(Stage) ((Node)event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    public void handleBubblesButtonAction(ActionEvent event) throws IOException
    {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../bubbles/Bubbles.fxml"));
        Parent root = loader.load();
        BubblesEndpoint bubblesEndpoint = new BubblesEndpoint(usernameTextField.getText(), loader.getController());
        bubblesEndpoint.connect();

        Scene scene = new Scene(root, RecRoom.WIDTH, RecRoom.HEIGHT);
        Stage  stage=(Stage) ((Node)event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();

    }
}
