package online.recroom.server.checkers.pieces;

import online.recroom.server.checkers.CheckersBoard;
import online.recroom.server.checkers.CoOrdinates;
import online.recroom.server.checkers.Color;

import java.util.Set;

/**
 * Created by theje on 4/28/2016.
 */
public class KingPiece extends Piece {

    public KingPiece(Color color, CheckersBoard cb) {
        super(color, cb);
    }

    @Override
    public boolean isProposedMoveValid(CoOrdinates destination) {
        return false;
    }

    @Override
    public void move(CoOrdinates destination) {

    }


    @Override
    public Set<CoOrdinates> getValidMoves() {
        return null;
    }
}
