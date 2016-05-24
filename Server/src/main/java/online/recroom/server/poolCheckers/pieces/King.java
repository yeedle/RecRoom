package online.recroom.server.poolCheckers.pieces;

import online.recroom.server.poolCheckers.board.Cell;

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
        return super.isRegularMove(destination) &&
                (Math.abs(this.getColumn() - destination.getColumn())) == 1;
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

}
