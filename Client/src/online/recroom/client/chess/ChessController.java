package online.recroom.client.chess;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by Yeedle on 5/18/2016 4:15 PM.
 */
public class ChessController implements Initializable
{

    StackPane[][] pieces = new StackPane[8][8];
    @FXML
    GridPane chessBoard;

    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        int k =0;
        for (int i =0; i < 8; i++)
            for (int j=0; j < 8; j++)
            {
                pieces[i][j] = (StackPane) chessBoard.getChildren().get(k++);
            }

        positionBishops();
        positionKings();
        positionKnights();
        positionPawns();
        positionQueens();
        positionRooks();
    }

    private void positionQueens()
    {
        pieces[3][7].getChildren().add(imageViewOf("pieces/black/queen.png"));
        pieces[3][0].getChildren().add(imageViewOf("pieces/white/queen.png"));
    }

    private ImageView imageViewOf(String url)
    {
        Image image = new Image(getClass().getResourceAsStream(url));
       ImageView iv = new ImageView(image);
        iv.setPreserveRatio(true);
        iv.setFitWidth(35);
        iv.setFitHeight(35);
        return iv;

    }

    private void positionKings()
    {
        pieces[4][7].getChildren().add(imageViewOf("pieces/black/king.png"));
        pieces[4][0].getChildren().add(imageViewOf("pieces/white/pawn.png"));
    }

    private void positionKnights()
    {
        int blackRow =7, whiteRow=0;

        pieces[1][whiteRow].getChildren().add(imageViewOf("pieces/white/knight.png"));
        pieces[6][whiteRow].getChildren().add(imageViewOf("pieces/white/knight.png"));
        pieces[1][blackRow].getChildren().add(imageViewOf("pieces/black/knight.png"));
        pieces[6][blackRow].getChildren().add(imageViewOf("pieces/black/knight.png"));

    }

    private void positionRooks()
    {
        pieces[0][7].getChildren().add(imageViewOf("pieces/black/rook.png"));
        pieces[7][7].getChildren().add(imageViewOf("pieces/black/rook.png"));
        pieces[0][0].getChildren().add(imageViewOf("pieces/white/rook.png"));
        pieces[7][0].getChildren().add(imageViewOf("pieces/white/rook.png"));
    }

    private void positionBishops()
    {
        pieces[2][7].getChildren().add(imageViewOf("pieces/black/bishop.png"));
        pieces[5][7].getChildren().add(imageViewOf("pieces/black/bishop.png"));
        pieces[2][0].getChildren().add(imageViewOf("pieces/white/bishop.png"));
        pieces[5][0].getChildren().add(imageViewOf("pieces/white/bishop.png"));
    }

    private void positionPawns()
    {
        for(int i = 0; i < 8; i++)
        {
            pieces[i][1].getChildren().add(imageViewOf("pieces/white/pawn.png"));
            pieces[i][6].getChildren().add(imageViewOf("pieces/black/pawn.png"));
        }
    }


}
