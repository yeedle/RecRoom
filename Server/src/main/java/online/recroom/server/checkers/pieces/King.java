package online.recroom.server.checkers.pieces;

import online.recroom.server.checkers.board.BoardCell;
import online.recroom.server.checkers.board.CoOrdinates;
import online.recroom.server.checkers.board.Color;

import java.util.Set;

/**
 * Created by theje on 4/28/2016.
 */
public class King extends Piece {

    public King(Color color, BoardCell cb) {
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
