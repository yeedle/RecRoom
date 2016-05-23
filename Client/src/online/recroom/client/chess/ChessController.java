package online.recroom.client.chess;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;

import java.net.URL;
import java.util.ResourceBundle;

import online.recroom.client.chess.pieces.Piece;
import online.recroom.client.chess.pieces.PieceType;
import online.recroom.client.chess.pieces.Player;

import static online.recroom.client.chess.pieces.PieceType.*;
import static online.recroom.client.chess.pieces.Player.BLACK;
import static online.recroom.client.chess.pieces.Player.WHITE;


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
        pieces[3][7].getChildren().add(new Piece(BLACK, QUEEN));
        pieces[3][0].getChildren().add(new Piece(WHITE, QUEEN));
    }


    private void positionKings()
    {
        pieces[4][7].getChildren().add(new Piece(BLACK, KING));
        pieces[4][0].getChildren().add(new Piece(WHITE, KING));
    }

    private void positionKnights()
    {
        int blackRow =7, whiteRow=0;

        pieces[1][whiteRow].getChildren().add(new Piece(WHITE, KNIGHT));
        pieces[6][whiteRow].getChildren().add(new Piece(WHITE, KNIGHT));
        pieces[1][blackRow].getChildren().add(new Piece(BLACK, KNIGHT));
        pieces[6][blackRow].getChildren().add(new Piece(BLACK, KNIGHT));

    }

    private void positionRooks()
    {
        pieces[0][7].getChildren().add(new Piece(BLACK, ROOK));
        pieces[7][7].getChildren().add(new Piece(BLACK, ROOK));
        pieces[0][0].getChildren().add(new Piece(WHITE, ROOK));
        pieces[7][0].getChildren().add(new Piece(WHITE, ROOK));
    }

    private void positionBishops()
    {
        pieces[2][7].getChildren().add(new Piece(BLACK, BISHOP));
        pieces[5][7].getChildren().add(new Piece(BLACK, BISHOP));
        pieces[2][0].getChildren().add(new Piece(WHITE, BISHOP));
        pieces[5][0].getChildren().add(new Piece(WHITE, BISHOP));
    }

    private void positionPawns()
    {
        for(int i = 0; i < 8; i++)
        {
            pieces[i][1].getChildren().add(new Piece(WHITE, PAWN));
            pieces[i][6].getChildren().add(new Piece(BLACK, PAWN));
        }
    }


}
