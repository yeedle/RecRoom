package online.recroom.server.chess.pieces;

/**
 * Created by Yeedle on 4/28/2016 7:15 PM.
 */
public class Position
{
    private int row;
    private int column;

    public Position(int row, int column)
    {
        this.row = row;
        this.column = column;
    }

    public void changePosition(int row, int column)
    {
        this.row = row;
        this.column = column;
    }
}
