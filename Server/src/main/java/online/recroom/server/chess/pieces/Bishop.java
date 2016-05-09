package online.recroom.server.chess.pieces;

import online.recroom.server.chess.Board;
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
        return false;
    }
}
