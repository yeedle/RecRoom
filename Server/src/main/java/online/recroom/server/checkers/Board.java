package online.recroom.server.checkers;

import online.recroom.server.checkers.pieces.Piece;

import java.util.Set;

/**
 * Created by theje on 4/28/2016.
 */
public class Board {

    private static final int ROWS = 8;
    private static final int COLUMNS = 8;

    private Set<Piece> blackPieces;
    private Set<Piece> whitePieces;

    private BoardCell[][] board;



    private void initializeBoard() {

    }

    public BoardCell getCell(int row, int column) {
        return board[row][column];
    }

    public Piece getPiece(int row, int column)
    {
        return getCell(row, column).piece;
    }

    public CoOrdinates getAPiecesCoOrdinates(Piece p) throws PieceNotFoundException {

        for (int row = 0; row < board.length; row++) {
            for (int column = 0; column < board[row].length; column++) {
                if (p == getPiece(row, column)) {
                    return getCell(row, column).coOrdinates;
                }
            }
        }
        throw new PieceNotFoundException();
    }

    public boolean isValidMove(Piece p, CoOrdinates coOrdinates)
    {
        return false;
    }

    public void movePiece(Piece p, int row, int column)
    {

    }

    private boolean playerHasLegalMoves(Set<Piece> pieces)
    {
        return false;
    }
}
