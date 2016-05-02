package online.recroom.server.chess;

import online.recroom.server.chess.pieces.*;

import static online.recroom.server.chess.pieces.Color.BLACK;
import static online.recroom.server.chess.pieces.Color.WHITE;

/**
 * Created by Yeedle on 5/2/2016 11:41 AM.
 */
class Board
{
    private final static int ROWS = 8;
    private final static int COLUMNS = 8;
    private Piece[][] pieces = new Piece[COLUMNS][ROWS];

    public Board()
    {
        initBoard();
    }


    private void initBoard()
    {
        positionRooks();
        positionKnights();
        positionPawns();
        positionKings();
        positionQueens();
        positionBishops();

    }

    private void positionQueens()
    {
        pieces[3][7] = new Queen(BLACK);
        pieces[3][0] = new Queen(WHITE);
    }

    private void positionKings()
    {
        pieces[4][7] = new King(BLACK);
        pieces[4][0] = new King(WHITE);
    }

    private void positionKnights()
    {
        int blackRow =7, whiteRow=0;

        pieces[1][whiteRow] = new Knight(WHITE);
        pieces[6][whiteRow] = new Knight(WHITE);
        pieces[1][blackRow] = new Knight(BLACK);
        pieces[6][blackRow] = new Knight(BLACK);

    }

    private void positionRooks()
    {
        pieces[0][7] = new Rook(BLACK);
        pieces[7][7] = new Rook(BLACK);
        pieces[0][0] = new Rook(WHITE);
        pieces[7][0] = new Rook(WHITE);
    }

    private void positionBishops()
    {
        pieces[2][7] = new Bishop(BLACK);
        pieces[5][7] = new Bishop(BLACK);
        pieces[2][0] = new Bishop(WHITE);
        pieces[5][0] = new Bishop(BLACK);
    }

    private void positionPawns()
    {
        for(int i=0; i < COLUMNS; i++)
        {
            pieces[i][1] = new Pawn(WHITE);
            pieces[i][6] = new Pawn(BLACK);
        }
    }

    Piece pieceInSquare(int row, int column)
    {

        return pieces[row][column];
    }

    public void execute(Move move) throws InvalidMove
    {

        int originColumn = move.getFromColumn();
        int originRow = move.getFromRow();
        int destinationColumn = move.getToColumn();
        int destinationRow = move.getToRow();
        Class classOfPiece = move.getClassOfPieceToMove();
        if (pieces[originColumn][originRow].getClass() != classOfPiece)
            throw new InvalidMove("piece not found");
    }
}
