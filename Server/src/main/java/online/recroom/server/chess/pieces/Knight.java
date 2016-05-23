package online.recroom.server.chess.pieces;

import online.recroom.server.chess.Board;
import online.recroom.server.chess.Coordinates;
import online.recroom.server.chess.Movement;

/**
 * Created by Yeedle on 5/2/2016 2:01 PM.
 */
public class Knight extends Piece
{
    public Knight(Player player)
    {
       super(player);
    }
    private Coordinates origin;
    private Coordinates destination;

    @Override
    public boolean isLegalMove(Movement move, Board board)
    {
        origin = move.origin;
        destination = move.destination;

        return movedTwoRowsAndOneColumn() || movedTwoColumnsAndOneRow();
    }

    private boolean movedTwoColumnsAndOneRow()
    {
        return (Math.abs(origin.row()-destination.row())==1) && (Math.abs(origin.column()-destination.column())==2);
    }

    private boolean movedTwoRowsAndOneColumn()
    {
        return (Math.abs(origin.row()-destination.row())==2) && (Math.abs(origin.column()-destination.column())==1);
    }
}
