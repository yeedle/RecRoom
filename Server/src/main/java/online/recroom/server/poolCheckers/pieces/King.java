package online.recroom.server.poolCheckers.pieces;

import online.recroom.server.poolCheckers.board.Cell;
import online.recroom.server.poolCheckers.board.CoOrdinates;
import online.recroom.server.poolCheckers.board.CoOrdinatesOutOfBoundsException;

/**
 * Created by theje on 5/7/2016.
 */
public class King extends Piece {

    public King(Piece piece) {
        super(piece.color);
        setCellImIn(piece.getCellImIn());
    }

    @Override
    protected boolean isRegularMove(Cell destination) {
        if (destination.isOccupied()) {
            return false;
        }
        return Math.abs(this.getRow() - destination.getRow()) == 1 &&
                Math.abs(this.getColumn() - destination.getColumn()) == 1;
    }

    @Override
    public boolean isCaptureMove(Cell destination) {
        if (destination.isOccupied()) {
            return false;
        }
        boolean isCapture = false;
        if (destination.getRow() == (this.getRow() + 2) && destination.getColumn() == (this.getColumn() + 2)) {
            isCapture = cellInBetweenIsCapturable(getRow() + 1, getColumn() + 1);
        } else if (destination.getRow() == (this.getRow() + 2) && destination.getColumn() == (this.getColumn() - 2)) {
            isCapture = cellInBetweenIsCapturable(getRow() - 1, getColumn() - 1);
        } else if (destination.getRow() == (this.getRow() - 2) && destination.getColumn() == (this.getColumn() - 2)) {
            isCapture = cellInBetweenIsCapturable(getRow() - 1, getColumn() - 1);
        } else if (destination.getRow() == (this.getRow() - 2) && destination.getColumn() == (this.getColumn() + 2)) {
            isCapture = cellInBetweenIsCapturable(getRow() - 1, getColumn() + 1);
        }
        return isCapture;
    }

    private boolean cellInBetweenIsCapturable(int row, int column) {
        try {
            Cell cellInBetween = getCellImIn().getBoardCellIsOn()
                    .getCell(new CoOrdinates(row, column));

            return cellInBetween.isOccupied() && cellInBetween.containsOpponent(this.color);
        } catch (CoOrdinatesOutOfBoundsException e) {
            return false;
        }
    }

}
