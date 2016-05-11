package online.recroom.server.poolCheckers.pieces;

import online.recroom.server.poolCheckers.board.Cell;
import online.recroom.server.poolCheckers.board.CoOrdinates;
import online.recroom.server.poolCheckers.board.CoOrdinatesOutOfBoundsException;


/**
 * Created by theje on 5/7/2016.
 */
public class BlackPiece extends Piece implements ICrownable {
    public BlackPiece() {
        super(Color.BLACK);
    }

    @Override
    protected boolean isRegularMove(Cell destination) {
        if (destination.isOccupied()) {
            return false;
        }
        if (destination.getRow() != (this.getRow() - 1)) {
            return false;
        }
        if (Math.abs(this.getColumn() - destination.getColumn()) == 1) {
            return true;
        }
        return false;
    }

    @Override
    public boolean isCaptureMove(Cell destination) {
        if (destination.isOccupied()) {
            return false;
        }
        if (destination.getRow() != (this.getRow() - 2)) {
            return false;
        }
        if (destination.getColumn() == (this.getColumn() + 2)) {
            Cell cellInBetween = null;
            try {
                cellInBetween = getCellImIn().getBoardCellIsOn()
                        .getCell(new CoOrdinates((getRow() - 1), getColumn() + 1));
            } catch (CoOrdinatesOutOfBoundsException e) {
                return false;
            }
            return cellInBetween.isOccupied() && cellInBetween.containsOpponent(this.color);
        }

        if (destination.getColumn() == (this.getColumn() - 2)) {
            Cell cellInBetween = null;
            try {
                cellInBetween = getCellImIn().getBoardCellIsOn()
                        .getCell(new CoOrdinates((getRow() - 1), getColumn() - 1));
            } catch (CoOrdinatesOutOfBoundsException e) {
                return false;
            }
            return cellInBetween.isOccupied() && cellInBetween.containsOpponent(this.color);
        }
        return false;
    }

    @Override
    public King crownMe() {
        return new King(this);
    }
}
