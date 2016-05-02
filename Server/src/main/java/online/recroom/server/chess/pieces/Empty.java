package online.recroom.server.chess.pieces;

import online.recroom.server.chess.Coordinates;

/**
 * Created by Yeedle on 5/2/2016 4:17 PM.
 */
public class Empty extends Piece
{
   public Empty()
   {
       super(Color.BLACK);
   }

    @Override
    public boolean isIllegalMove(Coordinates origin, Coordinates destination)
    {
        return false;
    }

}
