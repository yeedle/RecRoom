package online.recroom.client.chess;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by Yeedle on 5/18/2016 3:23 PM.
 */
public class ChessIntroController implements Initializable
{

    @FXML
    Label titleLbl;


    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        titleLbl.setText("Chess");
        System.out.println("hell from init");

    }

    @FXML
    public void newGame(ActionEvent event) throws IOException
    {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("chess.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        Stage stage=(Stage) ((Node)event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }



}
