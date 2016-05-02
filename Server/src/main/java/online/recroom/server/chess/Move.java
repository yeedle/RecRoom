package online.recroom.server.chess;

import online.recroom.server.chess.pieces.Color;
import online.recroom.server.chess.pieces.Empty;
import online.recroom.server.chess.pieces.Piece;

/**
 * Created by Yeedle on 4/11/2016 9:04 PM.
 */
public class Move
{
    public final Color madeBy;
    public final Coordinates origin;
    public final Coordinates destination;
    private Piece piece;

    public Move(Color madeByPlayer, Coordinates from, Coordinates to)
    {
        madeBy = madeByPlayer;
        this.origin = from;
        this.destination = to;
    }

    public Coordinates getOrigin() {return origin;}

    public Coordinates getDestination() {return destination;}


}
