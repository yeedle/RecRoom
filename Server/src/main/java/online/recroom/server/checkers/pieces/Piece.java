package online.recroom.server.checkers.pieces;

import online.recroom.server.checkers.board.Cell;
import online.recroom.server.checkers.board.CoOrdinates;
import online.recroom.server.checkers.board.Color;

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

    protected boolean isRegularMove(Cell destination) {
        if (destination.isOccupied())
            return false;
        if (destination.getRow() != (this.getRow() + 1)) {
            return false;
        }
        if (destination.getColumn() == (this.getColumn() + 1)
                || destination.getColumn() == (this.getColumn() - 1)) {
            return true;
        } else {
            return false;
        }
    }

    protected boolean isCaptureMove(Cell destination) {
        if (destination.isOccupied()) {
            return false;
        }
        if (destination.getRow() != (this.getRow() + 2)) {
            return false;
        }
        if (destination.getColumn() == (this.getColumn() + 2)) {
            Cell cellInBetween = cellPieceIsIn.getBoardCellIsOn()
                    .getCell(new CoOrdinates((getRow() + 1), getColumn() + 1));
            return cellInBetween.isOccupied();
        }
        if (destination.getColumn() == (this.getColumn() - 2)) {
            Cell cellInBetween = cellPieceIsIn.getBoardCellIsOn()
                    .getCell(new CoOrdinates((getRow() + 1), getColumn() - 1));
            return cellInBetween.isOccupied();
        } else {
            return false;
        }
    }

    public abstract Set<CoOrdinates> getValidDestinations();

}
