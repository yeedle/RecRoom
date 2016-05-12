package online.recroom.server.poolCheckers.pieces;

import online.recroom.server.poolCheckers.board.Board;
import online.recroom.server.poolCheckers.board.Cell;
import online.recroom.server.poolCheckers.board.CoOrdinates;
import online.recroom.server.poolCheckers.board.CoOrdinatesOutOfBoundsException;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by theje on 4/28/2016.
 */
public abstract class Piece {
    public final Color color;

    private Cell cellImIn;


    public Piece(Color color) {
        this.color = color;
    }

    public Cell getCellImIn() {
        return cellImIn;
    }

    public void setCellImIn(Cell cellImIn) {
        this.cellImIn = cellImIn;
    }

    protected CoOrdinates getCoordinates() {
        return cellImIn.getCoOrdinates();
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

    protected boolean isRegularMove(Cell destination) {
        return destination.isNotOccupied() && (Math.abs(this.getColumn() - destination.getColumn())) == 1;
    }

    public abstract boolean isCaptureMove(Cell destination);

    protected boolean cellInBetweenIsCapturable(int row, int column) {
        try {
            Cell cellInBetween = getCellImIn().getBoardCellIsOn()
                    .getCell(new CoOrdinates(row, column));

            return cellInBetween.isOccupied() && cellInBetween.containsOpponent(this.color);
        } catch (CoOrdinatesOutOfBoundsException e) {
            return false;
        }
    }
    
    public Set<CoOrdinates> getValidDestinations() throws CoOrdinatesOutOfBoundsException {
        HashSet<CoOrdinates> validDestinations = new HashSet<>();
        for (int row = 0; row < Board.ROWS; row++) {
            for (int column = 0; column < Board.COLUMNS; column++) {
                Cell theoreticalDestination = cellImIn.getBoardCellIsOn().getCell(new CoOrdinates(row, column));
                if (isDestinationValid(theoreticalDestination)) {
                    validDestinations.add(theoreticalDestination.getCoOrdinates());
                }
            }
        }
        return validDestinations;
    }

    public boolean isMovable() throws CoOrdinatesOutOfBoundsException {
        return !getValidDestinations().isEmpty();
    }

    public boolean isImmovable() throws CoOrdinatesOutOfBoundsException {
        return !isMovable();
    }

}
