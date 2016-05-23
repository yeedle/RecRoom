package online.recroom.client.chess.pieces;

/**
 * Created by Yeedle on 5/23/2016 1:20 PM.
 */
public class Coordinate
{
    int column;
    int row;

    public Coordinate(int column, int row)
    {
        this.column = column;
        this.row =row;
    }

    public int row()
    {
        return row;
    }
    public int column()
    {
        return column;
    }
    public static Coordinate byColumnAndRow(int column, int row)
    {
        return new Coordinate(column, row);
    }
}
