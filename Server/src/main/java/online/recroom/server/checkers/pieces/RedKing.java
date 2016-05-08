package online.recroom.server.checkers.pieces;

import online.recroom.server.checkers.board.Cell;
import online.recroom.server.checkers.board.CoOrdinates;

import java.util.Set;

/**
 * Created by theje on 4/28/2016.
 */
public class RedKing extends RedPiece {

    public RedKing(Cell cb) {
        super(cb);
    }

    @Override
    protected boolean isRegularMove(Cell destination) {
        if (super.isRegularMove(destination)) {
            return true;
        }
        if (destination.isOccupied())
            return false;
        if (destination.getRow() != (this.getRow() - 1)) {
            return false;
        }
        if (destination.getColumn() == (this.getColumn() + 1)
                || destination.getColumn() == (this.getColumn() - 1)) {
            return true;
        }
        return false;
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
            return cellInBetween.isOccupied() && cellInBetween.containsOpponent(this.color);
        }
        if (destination.getColumn() == (this.getColumn() - 2)) {
            Cell cellInBetween = cellPieceIsIn.getBoardCellIsOn()
                    .getCell(new CoOrdinates((getRow() - 1), getColumn() - 1));
            return cellInBetween.isOccupied() && cellInBetween.containsOpponent(this.color);
        }
        return false;
    }

    @Override
    public Set<CoOrdinates> getValidDestinations() {
        return null;
    }
}