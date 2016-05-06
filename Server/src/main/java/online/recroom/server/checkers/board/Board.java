package online.recroom.server.checkers.board;

import online.recroom.server.checkers.pieces.Piece;
import online.recroom.server.checkers.pieces.PieceNotFoundException;

import java.util.Set;

/**
 * Created by theje on 4/28/2016.
 */
public class Board {

    private static final int ROWS = 8;
    private static final int COLUMNS = 8;

    private final Color colorWeArePlayingOn;

    private BoardCell[][] board;

    private Set<Piece> blackPieces;
    private Set<Piece> whitePieces;

    public Board(Color colorWeArePlayingOn) {
        this.colorWeArePlayingOn = colorWeArePlayingOn;
    }

    private void initializeBoard() {

    }

    public Color getColorWeArePlayingOn() {
        return colorWeArePlayingOn;
    }

    private BoardCell getCell(int row, int column) {
        return board[row][column];
    }

    public BoardCell getCell(CoOrdinates coOrdinates) {
        return getCell(coOrdinates.row, coOrdinates.column);
    }

    public Piece getPiece(CoOrdinates coOrdinates)
    {
        return getCell(coOrdinates).getPiece();
    }

    public boolean isValidMove(Piece p, CoOrdinates coOrdinates)
    {
        return false;
    }

    public void movePiece(Piece p, CoOrdinates destination)
    {

    }

    private boolean playerHasLegalMoves(Set<Piece> pieces)
    {
        return false;
    }

    public CoOrdinates getCoOrdinatesOfPiece(Piece p) throws PieceNotFoundException {

        for (int row = 0; row < board.length; row++) {
            for (int column = 0; column < board[row].length; column++) {
                if (p == getPiece(new CoOrdinates(row, column))) {
                    return getCell(row, column).getCoOrdinates();
                }
            }
        }
        throw new PieceNotFoundException();
    }
}
