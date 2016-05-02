package online.recroom.server.chess.pieces;

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
    public boolean isMoveIllegal()
    {
        return false;
    }

}
