package online.recroom.server.checkers.board;

/**
 * Created by Yehuda Globerman on 5/1/2016.
 */
public class CoOrdinates {
    public final int row;
    public final int column;

    public CoOrdinates(int row, int column) throws CoOrdinatesOutOfBoundsException {
        if (row > Board.ROWS || row < 0 || column > Board.COLUMNS || column < 0) {
            throw new CoOrdinatesOutOfBoundsException();
        }
        this.row = row;
        this.column = column;
    }

    public CoOrdinates(CoOrdinates co) {
        row = co.row;
        column = co.column;
    }
}
