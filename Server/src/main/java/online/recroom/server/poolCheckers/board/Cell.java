package online.recroom.server.poolCheckers.board;

import online.recroom.server.poolCheckers.pieces.Piece;

/**
 * Created by theje on 5/6/2016.
 */
public class Cell {
    private final Board boardCellIsOn;
    private final CoOrdinates coOrdinates;
    private final Color color;
    private Piece piece;

    public <P extends Piece> Cell(Board boardCellIsOn, CoOrdinates coOrdinates, Color color, P piece) {
        this.boardCellIsOn = boardCellIsOn;
        this.coOrdinates = coOrdinates;
        this.color = color;
        this.piece = piece;
    }

    public Cell(Board boardCellIsOn, CoOrdinates coOrdinates, Color color) {
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

    public void clearCell() {
        setPiece(null);
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

    public boolean containsTeammate(online.recroom.server.poolCheckers.pieces.Color myColor) {
        return isOccupied() && getPiece().color == myColor;
    }

    public boolean containsOpponent(online.recroom.server.poolCheckers.pieces.Color myColor) {
        return !containsTeammate(myColor);
    }

    //    TODO IMPLEMENT
    public boolean isCrowningRow() {
        return false;
    }

}
