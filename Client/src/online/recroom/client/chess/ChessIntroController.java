package online.recroom.client.chess;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

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


}
