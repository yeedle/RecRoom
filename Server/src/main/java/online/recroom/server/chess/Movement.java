package online.recroom.server.chess;

import online.recroom.server.chess.pieces.Color;
import online.recroom.server.chess.pieces.Piece;

/**
 * Created by Yeedle on 4/11/2016 9:04 PM.
 */
public class Movement
{
    public final Color madeBy;
    public final Coordinates origin;
    public final Coordinates destination;
    public final Piece piece;

    public Movement(Color madeByPlayer, Coordinates from, Coordinates to, Piece piece)
    {
        madeBy = madeByPlayer;
        this.origin = from;
        this.destination = to;
        this.piece = piece;
    }
}
