package online.recroom.server.checkers.pieces;

import online.recroom.server.checkers.board.Board;
import online.recroom.server.checkers.board.Cell;
import online.recroom.server.checkers.board.CoOrdinates;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by theje on 4/28/2016.
 */
public abstract class Piece {
    public final Color color;

    protected Cell cellPieceIsIn;


    public Piece(Color color, Cell cb) {
        this.color = color;
        cellPieceIsIn = cb;
    }

    protected CoOrdinates getCoordinates() {
        return cellPieceIsIn.getCoOrdinates();
    }

    protected int getRow() {
        return getCoordinates().row;
    }

    protected int getColumn() {
        return getCoordinates().column;
    }

    public boolean isDestinationValid(Cell destination) {
        return destination.isColorWeArePlayingOn() && (isRegularMove(destination) || isCaptureMove(destination));
    }

    protected abstract boolean isRegularMove(Cell destination);

    protected abstract boolean isCaptureMove(Cell destination);

    public Set<CoOrdinates> getValidDestinations() {
        HashSet<CoOrdinates> validDestinations = new HashSet<>();
        for (int row = 0; row < Board.ROWS; row++) {
            for (int column = 0; column < Board.COLUMNS; column++) {
                Cell theoreticalDestination = cellPieceIsIn.getBoardCellIsOn().getCell(new CoOrdinates(row, column));
                if (isDestinationValid(theoreticalDestination)) {
                    validDestinations.add(theoreticalDestination.getCoOrdinates());
                }
            }
        }
        return validDestinations;
    }

}
