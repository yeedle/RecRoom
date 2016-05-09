package online.recroom.server.chess.pieces;

import online.recroom.server.chess.Board;
import online.recroom.server.chess.Movement;

/**
 * Created by Yeedle on 5/2/2016 1:44 PM.
 */
public class Rook extends Piece
{
    public Rook(Player player)
    {
        super(player);
    }

    @Override
    public boolean isLegalMove(Movement move, Board board)
    {
        return false;
    }
}
