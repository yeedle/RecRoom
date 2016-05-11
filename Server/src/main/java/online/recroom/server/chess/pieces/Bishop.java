package online.recroom.server.chess.pieces;

import online.recroom.server.chess.Board;
import online.recroom.server.chess.Coordinates;
import online.recroom.server.chess.Movement;

/**
 * Created by Yeedle on 5/2/2016 2:15 PM.
 */
public class Bishop extends Piece
{
    public Bishop(Player player)
    {
        super(player);
    }

    @Override
    public boolean isLegalMove(Movement move, Board board)
    {
        Coordinates origin =move.origin;
        Coordinates destination = move.destination;
        if (origin.row() < destination.row())
            //peiece is moving up

        if (origin.column() < destination.column())
            //piece is moving to the right
        return (Math.abs(origin.row()-destination.row())==(Math.abs(origin.column()-destination.column())));
    }
}
