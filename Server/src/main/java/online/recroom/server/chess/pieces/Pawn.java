package online.recroom.server.chess.pieces;

import online.recroom.server.chess.Board;
import online.recroom.server.chess.Movement;

/**
 * Created by Yeedle on 4/28/2016 7:03 PM.
 */
public class Pawn extends Piece
{
    public Pawn(Player player)
    {
        super(player);
    }

    @Override
    public boolean isLegalMove(Movement move, Board board)
    {
        return false;
    }
}
