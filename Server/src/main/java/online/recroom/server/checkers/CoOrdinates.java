package online.recroom.server.checkers;

/**
 * Created by Yehuda Globerman on 5/1/2016.
 */
public class CoOrdinates {
    private final int row;
    private final int column;


    public CoOrdinates(int row, int column) {
        this.row = row;
        this.column = column;
    }

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }
}
