package online.recroom.server.chess.pieces;

import online.recroom.server.chess.Board;
import online.recroom.server.chess.Coordinates;
import online.recroom.server.chess.IllegalCoordinateException;
import online.recroom.server.chess.Movement;

/**
 * Created by Yeedle on 5/2/2016 2:15 PM.
 */
public class Bishop extends Piece
{

    private int hDirection;
    private int vDirection;

    public Bishop(Player player)
    {
        super(player);
    }

    Coordinates origin;
    Coordinates destination;

    @Override
    public boolean isLegalMove(Movement move, Board board) throws IllegalCoordinateException
    {
        origin =move.origin;
        destination = move.destination;

        return pieceIsMovingDiagonally() && pieceIsNotJumpingOverOtherPieces(board);
    }

    private boolean pieceIsNotJumpingOverOtherPieces(Board board) throws IllegalCoordinateException
    {
        vDirection = origin.row() < destination.row()? 1 : -1;
        hDirection = origin.column() < destination.column()? 1 :-1;

        for (int i = 1; i < numberOfSquareBetweenOriginAndDestination(); i++)
            if (board.pieceInSquare(removedDiagonallyFromOriginBy(i)).isNotEmpty())
                return false;
        return true;
    }

    private Coordinates removedDiagonallyFromOriginBy(int i) throws IllegalCoordinateException
    {
        return Coordinates.byColumnAndRow(origin.column()+(i*hDirection), origin.row()+(i*vDirection));
    }

    private boolean pieceIsMovingDiagonally()
    {
        return Math.abs(origin.row()-destination.row())==(Math.abs(origin.column()-destination.column()));
    }
    private int numberOfSquareBetweenOriginAndDestination()
    {
        return Math.abs(origin.row()-destination.row());
    }
}
