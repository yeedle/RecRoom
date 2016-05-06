package online.recroom.server.checkers;


import online.recroom.server.checkers.board.CoOrdinates;
import online.recroom.server.checkers.board.Color;
import online.recroom.server.checkers.pieces.Piece;

public class Movement {
    public final Color madeBy;
    public final CoOrdinates origin;
    public final CoOrdinates destination;
    private final Piece piece;

    public Movement(Color madeByPlayer, CoOrdinates from, CoOrdinates to, Piece piece) {
        madeBy = madeByPlayer;
        this.origin = from;
        this.destination = to;
        this.piece = piece;
    }
}
