package online.recroom.server.checkers.pieces;

import online.recroom.server.checkers.board.Cell;
import online.recroom.server.checkers.board.CoOrdinates;
import online.recroom.server.checkers.board.Color;

import java.util.Set;

/**
 * Created by Yehuda Globerman on 4/28/2016.
 */
public class Man extends Piece {

    public Man(Color color, Cell cb) {
        super(color, cb);
    }

    @Override
    public Set<CoOrdinates> getValidDestinations() {
        return null;
    }
}
