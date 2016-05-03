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
        return (Math.abs(origin.row() - origin.column()) == 1 && origin.column() == destination.column())
                || (Math.abs(origin.row() - origin.column()) == 1) && origin.row() == destination.row();
    }
}
