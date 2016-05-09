package online.recroom.server.chess.pieces;

import online.recroom.server.chess.Board;
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

    @Override
    public boolean isLegalMove(Movement move, Board board)
    {
        return false;
    }
}
