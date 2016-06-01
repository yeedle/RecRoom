package online.recroom.server.chess;

import online.recroom.server.chess.pieces.Player;

/**
 * Created by Yeedle on 4/11/2016 9:04 PM.
 */
public class Movement
{
    public final Coordinates origin;
    public final Coordinates destination;

    public Movement(Player madeByPlayer, Coordinates from, Coordinates to)
    {
        this.origin = from;
        this.destination = to;
    }
}
