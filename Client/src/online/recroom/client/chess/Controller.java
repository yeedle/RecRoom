package online.recroom.client.chess;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import online.recroom.client.Scener;
import online.recroom.client.chess.pieces.Coordinate;
import online.recroom.client.chess.pieces.Piece;

import javax.websocket.*;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import static online.recroom.client.chess.pieces.PieceType.*;
import static online.recroom.client.chess.pieces.Player.BLACK;
import static online.recroom.client.chess.pieces.Player.WHITE;


/**
 * Created by Yeedle on 5/18/2016 4:15 PM.
 */
public class Controller
{

    StackPane[][] pieces = new StackPane[8][8];
    @FXML
    GridPane chessBoard;
    Endpoint endpoint;
    Coordinate origin;
    Coordinate destination;

    public Stage getStage(){
        return (Stage)chessBoard.getScene().getWindow();
    }

    public void initialize()
    {
        int k =0;
        for (int i =0; i < 8; i++)
            for (int j=7; j >= 0; j--)
            {
                 Square square= (Square) chessBoard.getChildren().get(k++);
                pieces[i][j] = square;
                square.setCoordinate(Coordinate.byColumnAndRow(i, j));
            }

        positionBishops();
        positionKings();
        positionKnights();
        positionPawns();
        positionQueens();
        positionRooks();
        for (Node node : chessBoard.getChildren())
        {
            node.setOnMouseClicked(e ->  {
                if (origin == null)
                {
                    origin = ((Square) node).getCoordinate();
                    System.out.println("origin");
                } else
                {
                    destination = ((Square) node).getCoordinate();
                    endpoint.sendMessage(origin, destination);
                    destination = null;
                    origin = null;
                }
            });
        }

        chessBoard.sceneProperty().addListener((observableScene, oldScene, newScene) -> attachKeyListners(oldScene, newScene));

    }

    private void attachKeyListners(Scene oldScene, Scene newScene)
    {
        if (oldScene == null && newScene != null)
            newScene.setOnKeyPressed(e -> handleKeyStrokes(e));

    }

    public void setEndpoint(Endpoint endpoint)
    {
        this.endpoint =endpoint;
    }

    private void positionQueens()
    {
        pieces[3][7].getChildren().add(new Piece(BLACK, QUEEN, Coordinate.byColumnAndRow(3, 7)));
        pieces[3][0].getChildren().add(new Piece(WHITE, QUEEN, Coordinate.byColumnAndRow(3, 0)));
    }


    private void positionKings()
    {
        pieces[4][7].getChildren().add(new Piece(BLACK, KING, Coordinate.byColumnAndRow(4, 7)));
        pieces[4][0].getChildren().add(new Piece(WHITE, KING, Coordinate.byColumnAndRow(4, 0)));
    }

    private void positionKnights()
    {
        int blackRow =7, whiteRow=0;

        pieces[1][whiteRow].getChildren().add(new Piece(WHITE, KNIGHT, Coordinate.byColumnAndRow(1, whiteRow)));
        pieces[6][whiteRow].getChildren().add(new Piece(WHITE, KNIGHT, Coordinate.byColumnAndRow(6, whiteRow)));
        pieces[1][blackRow].getChildren().add(new Piece(BLACK, KNIGHT, Coordinate.byColumnAndRow(1, blackRow)));
        pieces[6][blackRow].getChildren().add(new Piece(BLACK, KNIGHT, Coordinate.byColumnAndRow(6, blackRow)));

    }

    private void positionRooks()
    {
        pieces[0][7].getChildren().add(new Piece(BLACK, ROOK, Coordinate.byColumnAndRow(0, 7)));
        pieces[7][7].getChildren().add(new Piece(BLACK, ROOK, Coordinate.byColumnAndRow(7, 7)));
        pieces[0][0].getChildren().add(new Piece(WHITE, ROOK, Coordinate.byColumnAndRow(0 ,0)));
        pieces[7][0].getChildren().add(new Piece(WHITE, ROOK, Coordinate.byColumnAndRow(7, 0)));
    }

    private void positionBishops()
    {
        pieces[2][7].getChildren().add(new Piece(BLACK, BISHOP, Coordinate.byColumnAndRow(2, 7)));
        pieces[5][7].getChildren().add(new Piece(BLACK, BISHOP, Coordinate.byColumnAndRow(5, 7)));
        pieces[2][0].getChildren().add(new Piece(WHITE, BISHOP, Coordinate.byColumnAndRow(2, 0)));
        pieces[5][0].getChildren().add(new Piece(WHITE, BISHOP, Coordinate.byColumnAndRow(5, 0)));
    }

    private void positionPawns()
    {
        for(int i = 0; i < 8; i++)
        {
            pieces[i][1].getChildren().add(new Piece(WHITE, PAWN, Coordinate.byColumnAndRow(i ,1)));
            pieces[i][6].getChildren().add(new Piece(BLACK, PAWN, Coordinate.byColumnAndRow(i, 6)));
        }
    }

    private void handleKeyStrokes(KeyEvent ke)
    {
        if (ke.getCode().equals(KeyCode.BACK_SPACE))
        {
            final String PATH = "../welcome/welcome.fxml";
            FXMLLoader loader = Scener.getLoader(PATH, this.getClass());
            try
            {
                Parent root = loader.load();
                Scener.showScene(getStage(), root);
            } catch (IOException e) {e.printStackTrace();}

        }
    }

}
