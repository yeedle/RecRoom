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

    @FXML
    private void handleChessButtonAction(ActionEvent event) throws IOException
    {
        final String PATH = "../chess/Chess.fxml";
        FXMLLoader loader = getLoader(PATH);
        Parent root = loader.load();
        showScene(event, root);
    }


    public void handleTicTacToeButtonAction(ActionEvent event) throws IOException
    {
        final String PATH = "../ticTacToe/TicTacToe.fxml";
        FXMLLoader loader = getLoader(PATH);
        Parent root = loader.load();
        showScene(event, root);
    }

    public void handleBubblesButtonAction(ActionEvent event) throws IOException
    {
        final String PATH = "../bubbles/Bubbles.fxml";
        FXMLLoader loader = Scener.getLoader(PATH, this.getClass());
        Parent root = loader.load();
        Endpoint endpoint = new Endpoint(usernameTextField.getText(), loader.getController());
        try {
            endpoint.connect();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }


        Scener.showScene(event, root);

    }

    private FXMLLoader getLoader(String PATH)
    {
        return new FXMLLoader(getClass().getResource(PATH));
    }

    private void showScene(ActionEvent event, Parent root)
    {
        Scene scene = new Scene(root);
        Stage stage=(Stage) ((Node)event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }
}
