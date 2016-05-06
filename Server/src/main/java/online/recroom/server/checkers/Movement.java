package online.recroom.server.checkers;

import online.recroom.server.chess.Coordinates;
import online.recroom.server.chess.pieces.Piece;

/**
 * Created by theje on 5/6/2016.
 */
public class Movement {
    public final online.recroom.server.chess.pieces.Color madeBy;
    public final Coordinates origin;
    public final Coordinates destination;
    private Piece piece;

    public Movement(online.recroom.server.chess.pieces.Color madeByPlayer, Coordinates from, Coordinates to) {
        madeBy = madeByPlayer;
        this.origin = from;
        this.destination = to;
    }
}
