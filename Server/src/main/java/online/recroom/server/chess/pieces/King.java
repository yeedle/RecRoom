package online.recroom.server.chess.pieces;

import online.recroom.server.chess.Coordinates;

/**
 * Created by Yeedle on 5/2/2016 2:39 PM.
 */
public class King extends Piece
{
    public King(Color color)
    {
        super(color);
    }

    @Override
    public boolean isIllegalMove(Coordinates origin, Coordinates destination)
    {
        return isSameColumnOffByOneRow(origin, destination)
                || isSameRowAndOffByOneColumn(origin, destination);
    }

    private boolean isSameRowAndOffByOneColumn(Coordinates origin, Coordinates destination)
    {
        return (Math.abs(origin.column() - destination.column()) == 1) && origin.row() == destination.row();
    }

    private boolean isSameColumnOffByOneRow(Coordinates origin, Coordinates destination)
    {
        return Math.abs(origin.row() - destination.row()) == 1 && origin.column() == destination.column();
    }
}
