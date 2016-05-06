package online.recroom.server.checkers.board;

/**
 * Created by Yehuda Globerman on 5/1/2016.
 */
public class CoOrdinates {
    protected final int row;
    protected final int column;

    public CoOrdinates(int row, int column) {
        this.row = row;
        this.column = column;
    }

    public CoOrdinates(CoOrdinates co) {
        row = co.row;
        column = co.column;
    }
}
