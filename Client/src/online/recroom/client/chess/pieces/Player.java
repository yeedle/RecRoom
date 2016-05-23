package online.recroom.client.chess.pieces;

/**
 * Created by Yeedle on 5/23/2016 12:47 PM.
 */
public enum Player
{
    BLACK, WHITE;

    @Override
    public String toString()
    {
        return super.toString().toLowerCase();
    }
}
