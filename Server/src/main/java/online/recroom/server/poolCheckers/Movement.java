package online.recroom.server.poolCheckers;


import online.recroom.server.poolCheckers.board.CoOrdinates;
import online.recroom.server.poolCheckers.board.Color;
import online.recroom.server.poolCheckers.pieces.Piece;

public class Movement {
    public final Color madeBy;
    public final CoOrdinates origin;
    public final CoOrdinates destination;
    public final Piece piece;

    public Movement(Color madeByPlayer, CoOrdinates from, CoOrdinates to, Piece piece) {
        madeBy = madeByPlayer;
        this.origin = from;
        this.destination = to;
        this.piece = piece;
    }
}
