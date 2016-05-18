package online.recroom.client.chess;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by Yeedle on 5/18/2016 4:15 PM.
 */
public class ChessController implements Initializable
{

    StackPane[][] array = new StackPane[8][8];
    @FXML
    GridPane chessBoard;

    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        int k =0;
        for (int i =0; i < 8; i++)
            for (int j=0; j < 8; j++)
            {
                array[i][j] = (StackPane) chessBoard.getChildren().get(k++);
                Image image = new Image(ChessController.class.getResourceAsStream("pieces/white/pawn.png"));
                array[i][j].getChildren().add(new ImageView(image));

            }
    }


}
