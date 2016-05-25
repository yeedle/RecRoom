package online.recroom.client.ticTacToe;

/**
 * Created by Yehuda Globerman on 4/17/2016.
 */
public class Move
{
    private int row;

    private int column;

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getColumn() {
        return column;
    }

    public void setColumn(int column) {
        this.column = column;
    }

    public String toString()
    {
        return "row " + row + "; column " + column;
    }
}
