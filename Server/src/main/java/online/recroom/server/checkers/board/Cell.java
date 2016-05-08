package online.recroom.server.checkers.board;

import online.recroom.server.checkers.pieces.Piece;

/**
 * Created by theje on 5/6/2016.
 */
public class Cell {
    private final Board boardCellIsOn;
    private final CoOrdinates coOrdinates;
    private final Color color;
    private Piece piece;

    private Cell(Board boardCellIsOn, CoOrdinates coOrdinates, Color color) {
        this.boardCellIsOn = boardCellIsOn;
        this.coOrdinates = coOrdinates;
        this.color = color;
    }

    public Board getBoardCellIsOn() {
        return boardCellIsOn;
    }

    public CoOrdinates getCoOrdinates() {
        return coOrdinates;
    }

    public int getRow() {
        return getCoOrdinates().row;
    }

    public int getColumn() {
        return getCoOrdinates().column;
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
        return getPiece() != null;
    }

    public boolean isNotOccupied() {
        return !isOccupied();
    }

    public boolean containsTeammate(online.recroom.server.checkers.pieces.Color myColor) {
        return isOccupied() && getPiece().color == myColor;
    }

    public boolean containsOpponent(online.recroom.server.checkers.pieces.Color myColor) {
        return !containsTeammate(myColor);
    }

    //    TODO IMPLEMENT
    public boolean isCrowningRow() {
        return false;
    }

}
