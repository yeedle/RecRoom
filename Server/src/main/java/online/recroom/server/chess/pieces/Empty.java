package online.recroom.server.chess.pieces;

import online.recroom.server.chess.Board;
import online.recroom.server.chess.Coordinates;
import online.recroom.server.chess.Movement;

/**
 * Created by Yeedle on 5/2/2016 4:17 PM.
 */
public class Empty extends Piece
{
   public Empty()
   {
       super(Player.BLACK);
   }


    public boolean isIllegalMove(Coordinates origin, Coordinates destination)
    {
        return false;
    }

    @Override
    public boolean isLegalMove(Movement move, Board board)
    {
        return false;
    }
}
