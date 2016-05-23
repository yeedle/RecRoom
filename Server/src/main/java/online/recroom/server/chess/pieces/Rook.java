package online.recroom.server.chess.pieces;

import online.recroom.server.chess.Board;
import online.recroom.server.chess.Coordinates;
import online.recroom.server.chess.IllegalCoordinateException;
import online.recroom.server.chess.Movement;

/**
 * Created by Yeedle on 5/2/2016 1:44 PM.
 */
public class Rook extends Piece
{
    public Rook(Player player)
    {
        super(player);
    }

    Board board;
    Coordinates origin;
    Coordinates destination;
    private int hDirection;
    private int vDirection;

    @Override
    public boolean isLegalMove(Movement move, Board board) throws IllegalCoordinateException
    {

        origin = move.origin;
        destination = move.destination;
        this.board = board;

        return (movedVertically() && noPiecesInWayVertically()) || movedHorizontally() && noPiecesInWayHorizontally();
    }

    private boolean movedHorizontally()
    {
        return origin.row() == destination.row();
    }

    private boolean movedVertically()
    {
        return origin.column()==destination.column();
    }


    private boolean noPiecesInWayVertically() throws IllegalCoordinateException
    {
        vDirection = origin.row() < destination.row()? 1 : -1;
        for (int i =1; i < numberOfSquareVerticallyBetweenOriginAndDestination(); i++)
            if(board.pieceInSquare(removedVerticallyFromOriginBy(i)).isNotEmpty())
                return false;
        return true;
    }

    private boolean noPiecesInWayHorizontally() throws IllegalCoordinateException
    {
        hDirection = origin.column() < destination.column()? 1 : -1;

        for (int i =1; i < numberOfSquareHorizontallyBetweenOriginAndDestination(); i++)
            if(board.pieceInSquare(removedHorizontallyFromOriginBy(i)).isNotEmpty())
                return false;
        return true;
    }

    private int numberOfSquareVerticallyBetweenOriginAndDestination()
    {
        return Math.abs(origin.row()-destination.row());
    }
    private int numberOfSquareHorizontallyBetweenOriginAndDestination() { return Math.abs(origin.column()-destination.column());}

    private Coordinates removedVerticallyFromOriginBy(int i) throws IllegalCoordinateException
    {
        return Coordinates.byColumnAndRow(origin.column(), origin.row()+(i*vDirection));
    }
    private Coordinates removedHorizontallyFromOriginBy(int i) throws IllegalCoordinateException
    {
        return Coordinates.byColumnAndRow(origin.column()+(i*hDirection), origin.row());
    }
}
