package online.recroom.server.checkers.board;

import online.recroom.server.checkers.Movement;
import online.recroom.server.checkers.pieces.Piece;

import java.util.HashSet;
import java.util.LinkedList;

/**
 * Created by theje on 4/28/2016.
 */
public class Board {

    private static final int ROWS = 8;
    private static final int COLUMNS = 8;

    private final Color colorWeArePlayingOn;

    private Cell[][] board;

    private LinkedList<Movement> movements;
    private HashSet<Piece> blackPieces;
    private HashSet<Piece> whitePieces;

    public Board(Color colorWeArePlayingOn) {
        this.colorWeArePlayingOn = colorWeArePlayingOn;
    }

    private void initializeBoard() {

    }

    public Color getColorWeArePlayingOn() {
        return colorWeArePlayingOn;
    }

    private Cell getCell(int row, int column) {
        return board[row][column];
    }

    public Cell getCell(CoOrdinates coOrdinates) {
        return getCell(coOrdinates.row, coOrdinates.column);
    }

    public Piece getPiece(CoOrdinates coOrdinates)
    {
        return getCell(coOrdinates).getPiece();
    }

    public boolean isValidMove(Movement proposedMovement)
    {
        return false;
    }

    public void movePiece(Movement movement)
    {

    }

    private boolean playerHasLegalMoves(HashSet<Piece> pieces)
    {
        return false;
    }

}
