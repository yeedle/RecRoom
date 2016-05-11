package online.recroom.server.chess;

import online.recroom.server.chess.pieces.Player;

/**
 * Created by Yeedle on 5/10/2016 9:36 PM.
 */
public enum Status
{
    CHECK, NONE, CHECKMATE, STALEMATE;
    Player color;

    public Player calledOn()
    {
        return color;
    }
}
