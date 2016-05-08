package online.recroom.server.checkers.pieces;

import online.recroom.server.checkers.board.Cell;
import online.recroom.server.checkers.board.CoOrdinates;
import online.recroom.server.checkers.board.Color;

import java.util.Set;

/**
 * Created by theje on 4/28/2016.
 */
public class King extends Piece {

    public King(Color color, Cell cb) {
        super(color, cb);
    }

    @Override
    protected boolean isRegularMove(Cell destination) {
        if (super.isRegularMove(destination)) {
            return true;
        } else {
            if (destination.isOccupied())
                return false;
            if (destination.getRow() != (this.getRow() - 1)) {
                return false;
            }
            if (destination.getColumn() == (this.getColumn() + 1)
                    || destination.getColumn() == (this.getColumn() - 1)) {
                return true;
            } else {
                return false;
            }
        }
    }

    @Override
    protected boolean isCaptureMove(Cell destination) {
        if (super.isCaptureMove(destination)) {
            return true;
        }
        if (destination.isOccupied()) {
            return false;
        }
        if (destination.getRow() != (this.getRow() - 2)) {
            return false;
        }
        if (destination.getColumn() == (this.getColumn() + 2)) {
            Cell cellInBetween = cellPieceIsIn.getBoardCellIsOn()
                    .getCell(new CoOrdinates((getRow() - 1), getColumn() + 1));
            return cellInBetween.isOccupied();
        }
        if (destination.getColumn() == (this.getColumn() - 2)) {
            Cell cellInBetween = cellPieceIsIn.getBoardCellIsOn()
                    .getCell(new CoOrdinates((getRow() - 1), getColumn() - 1));
            return cellInBetween.isOccupied();
        } else {
            return false;
        }
    }

    @Override
    public Set<CoOrdinates> getValidDestinations() {
        return null;
    }
}
