package online.recroom.client.welcome;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import online.recroom.client.Scener;
import online.recroom.client.bubbles.Controller;
import online.recroom.client.bubbles.Endpoint;

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

    private Stage getStage()
    {
        return (Stage) usernameTextField.getScene().getWindow();
    }

    @FXML
    private void handleChessButtonAction(ActionEvent event) throws IOException
    {
       FXMLLoader loader = loadScene("../chess/Chess.fxml");
        online.recroom.client.chess.Endpoint endpoint = new online.recroom.client.chess.Endpoint(usernameTextField.getText(), loader.getController());
        try
        {
            endpoint.connect();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }


    public void handleTicTacToeButtonAction(ActionEvent event) throws IOException
    {
        loadScene("../ticTacToe/TicTacToe.fxml");
    }

    public void handleBubblesButtonAction(ActionEvent event) throws IOException
    {
        FXMLLoader loader = loadScene("../bubbles/Bubbles.fxml");
        Endpoint endpoint = new Endpoint(usernameTextField.getText(), loader.getController());
        try {
            endpoint.connect();
        }
        catch (Exception e)
        {
            e.printStackTrace();
            ((Controller)loader.getController()).console(e.getMessage() +" Can't connect to the server.");
        }

    }

    private FXMLLoader loadScene(String url) throws IOException
    {
        FXMLLoader loader = Scener.getLoader(url, this.getClass());
        Parent root = loader.load();
        Scener.showScene(getStage(), root);
        return loader;
    }
}
