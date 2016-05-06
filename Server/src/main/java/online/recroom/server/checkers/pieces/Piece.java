package online.recroom.server.checkers.pieces;

import online.recroom.server.checkers.board.BoardCell;
import online.recroom.server.checkers.board.CoOrdinates;
import online.recroom.server.checkers.board.Color;

import java.util.Set;

/**
 * Created by theje on 4/28/2016.
 */
public abstract class Piece {
    private final Color color;

    private final BoardCell cellPieceIsIn;


    public Piece(Color color, BoardCell cb) {
        this.color = color;
        cellPieceIsIn = cb;
    }


    public abstract boolean isProposedMoveValid(CoOrdinates destination);

    public abstract void move(CoOrdinates destination);

    public abstract Set<CoOrdinates> getValidMoves();

    public CoOrdinates getCoordinates() throws PieceNotFoundException {
        return cellPieceIsIn.getCoOrdinates();
    }
}
