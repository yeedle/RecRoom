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
    Move move;

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
        addEmptyPieces();

    }

    private void addEmptyPieces()
    {
        for(int i = 0; i < COLUMNS; i++)
        {
            for(int j = 2; j < 6; j++)
                pieces[i][j] = new Empty();
        }
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
        for(int i = 0; i < COLUMNS; i++)
        {
            pieces[i][1] = new Pawn(WHITE);
            pieces[i][6] = new Pawn(BLACK);
        }
    }

    Piece pieceInSquare(int column, int row)
    {

        return pieces[column][row];
    }

    public void execute(Move move) throws InvalidMoveException, IllegalMoveException
    {
        Piece pieceToMove = pieceInSquare(move.origin.column(), move.origin.row());
        checkForErrors(move, pieceToMove);
        move(move.origin.row(), move.origin.column(), move.destination.row(), move.destination.column());
    }

    private void checkForErrors(Move move, Piece pieceToMove) throws InvalidMoveException, IllegalMoveException
    {
        if(isEmpty(pieceToMove))
            throw new InvalidMoveException("piece not found");
        if (destinationIsOccupiedByOwn(move))
            throw new IllegalMoveException("Occupied by own piece");
        if(pieceToMove.isIllegalMove(move.origin, move.destination))
            throw new IllegalMoveException("Piece can't do that move");
    }

    private boolean destinationIsOccupiedByOwn(Move move)
    {
        return pieces[move.destination.column()][move.destination.row()].getColor() == move.madeBy;
    }

    private boolean isEmpty(Piece pieceToMove)
    {
        return pieceToMove instanceof Empty;
    }

    private void move(int fromRow, int fromColumn, int toRow, int toColumn)
    {
        Piece piece = pieces[fromColumn][fromRow];
        pieces[fromColumn][fromRow] = new Empty();
        pieces[toColumn][toRow] = piece;
    }


}
