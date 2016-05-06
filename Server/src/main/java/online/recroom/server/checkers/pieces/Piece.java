package online.recroom.server.checkers.pieces;

import online.recroom.server.checkers.Board;
import online.recroom.server.checkers.CoOrdinates;
import online.recroom.server.checkers.Color;
import online.recroom.server.checkers.PieceNotFoundException;

import java.util.Set;

/**
 * Created by theje on 4/28/2016.
 */
public abstract class Piece {
    private final Color color;

    private final Board boardPieceIsOn;


    public Piece(Color color, Board cb) {
        this.color = color;
        boardPieceIsOn = cb;
    }


    public abstract boolean isProposedMoveValid(CoOrdinates destination);

    public abstract void move(CoOrdinates destination);

    public abstract Set<CoOrdinates> getValidMoves();

    public CoOrdinates getCurrentCoordinates() throws PieceNotFoundException {
        return boardPieceIsOn.getCoOrdinatesOfPiece(this);
    }
}
