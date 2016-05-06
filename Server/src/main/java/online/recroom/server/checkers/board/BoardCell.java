package online.recroom.server.checkers.board;

import online.recroom.server.checkers.pieces.Piece;

/**
 * Created by theje on 5/6/2016.
 */
public class BoardCell {
    private final Board boardCellIsOn;
    private final CoOrdinates coOrdinates;
    private final Color color;
    private Piece piece;

    private BoardCell(Board boardCellIsOn, CoOrdinates coOrdinates, Color color) {
        this.boardCellIsOn = boardCellIsOn;
        this.coOrdinates = coOrdinates;
        this.color = color;
    }

    public CoOrdinates getCoOrdinates() {
        return coOrdinates;
    }

    public Piece getPiece() {
        return piece;
    }

    public void setPiece(Piece piece) {
        this.piece = piece;
    }

    public boolean isColorWeArePlayingOn() {
        return boardCellIsOn.getColorWeArePlayingOn() == color;
    }

    public boolean isNotColorWePlayOn() {
        return !isColorWeArePlayingOn();
    }

    public boolean isOccupied() {
        return this.piece != null;
    }

    public boolean isNotOccupied() {
        return !isOccupied();
    }

    //    TODO IMPLEMENT
    public boolean isCrowningRow() {
        return false;
    }

}
