package online.recroom.server.chess;

import online.recroom.server.chess.pieces.Color;
import online.recroom.server.chess.pieces.Pawn;
import online.recroom.server.chess.pieces.Piece;

/**
 * Created by Yeedle on 5/2/2016 11:41 AM.
 */
class Board
{
    private final static int ROWS = 8;
    private final static int COLUMNS = 8;
    private Square[][] squares = new Square[ROWS][COLUMNS];


    private class Square
    {
        private Piece piece;

        private Square(Piece piece)
        {
            this.piece = piece;
        }

        Piece getPiece() {return piece;}

        void setPiece(Piece piece) {this.piece = piece;}
    }

    void initBoard()
    {
        for(int i=0; i < COLUMNS; i++)
        {
            Pawn pawn = new Pawn(Color.WHITE);
            squares[i][1] = new Square(pawn);
            pawn = new Pawn(Color.BLACK);
            squares[i][6] = new Square(pawn);
        }

    }

    Piece pieceInSquare(int row, int column)
    {

        return squares[row][column].getPiece();
    }
}
