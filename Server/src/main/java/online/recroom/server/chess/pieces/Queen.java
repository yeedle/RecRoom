package online.recroom.server.chess.pieces;

import online.recroom.server.chess.Board;
import online.recroom.server.chess.Coordinates;
import online.recroom.server.chess.IllegalCoordinateException;
import online.recroom.server.chess.Movement;

/**
 * Created by Yeedle on 5/2/2016 2:38 PM.
 */
public class Queen extends Piece
{
    public Queen(Player player)
    {
        super(player);
    }
    Coordinates origin;
    Coordinates destination;
    Board board;
    int vDirection, hDirection;

    @Override
    public boolean isLegalMove(Movement move, Board board) throws IllegalCoordinateException
    {
        origin = move.origin;
        destination = move.destination;
        this.board = board;

        return movedDiagonally() || movedStraight();

    }

    private boolean movedDiagonally() throws IllegalCoordinateException
    {
        return Math.abs(origin.column()-destination.column())==Math.abs(origin.row()-destination.row())
                && noPiecesInWayDiagonally();
    }

    private boolean movedStraight() throws IllegalCoordinateException
    {
        return (origin.column()==destination.column() && noPiecesInWayVertically())
                ||
                origin.row() == destination.row() && noPiecesInWayHorizontally();
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

    private boolean noPiecesInWayDiagonally() throws IllegalCoordinateException
    {
         vDirection = origin.row() < destination.row()? 1 : -1;
        hDirection = origin.column() < destination.column()? 1 :-1;

        for (int i = 1; i < numberOfSquareVerticallyBetweenOriginAndDestination(); i++)
            if (board.pieceInSquare(removedDiagonallyFromOriginBy(i)).isNotEmpty())
                return false;
        return true;
    }

    private int numberOfSquareVerticallyBetweenOriginAndDestination()
    {
        return Math.abs(origin.row()-destination.row());
    }
    private int numberOfSquareHorizontallyBetweenOriginAndDestination() { return Math.abs(origin.column()-destination.column());}


    private Coordinates removedDiagonallyFromOriginBy(int i) throws IllegalCoordinateException {
        return Coordinates.byColumnAndRow(origin.column()+(i*hDirection), origin.row()+(i*vDirection));
    }
    private Coordinates removedVerticallyFromOriginBy(int i) throws IllegalCoordinateException
    {
        return Coordinates.byColumnAndRow(origin.column(), origin.row()+(i*vDirection));
    }
    private Coordinates removedHorizontallyFromOriginBy(int i) throws IllegalCoordinateException
    {
        return Coordinates.byColumnAndRow(origin.column()+(i*hDirection), origin.row());
    }
}

