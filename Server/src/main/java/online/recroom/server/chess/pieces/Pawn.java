package online.recroom.server.chess.pieces;

import online.recroom.server.chess.Board;
import online.recroom.server.chess.Coordinates;
import online.recroom.server.chess.Movement;

/**
 * Created by Yeedle on 4/28/2016 7:03 PM.
 */
public class Pawn extends Piece
{
    public Pawn(Player player)
    {
        super(player);
    }

    private Coordinates origin;
    private Coordinates destination;

    @Override
    public boolean isLegalMove(Movement move, Board board)
    {
        origin = move.origin;
        destination = move.destination;

        return player().equals(Player.BLACK) ? isLegalBlackMove(board) : isLegalWhiteMove(board);
    }

    private boolean isLegalBlackMove(Board board)
    {
        if (board.pieceInSquare(destination).isNotEmpty())
            return movedOneDiagonalDown();
        return ((inInitialPositionAndMovedTwoDown() || movedOneRowDown()) && stayedInSameColumn());
    }

    private boolean isLegalWhiteMove(Board board)
    {
        if (board.pieceInSquare(destination).isNotEmpty())
            return movedOneDiagonalUp();
        return (inInitialPositionAndMovedTwoRowsUp() || movedOneRowUp()) && stayedInSameColumn();
    }

    private boolean movedOneRowUp()
    {
        return destination.row() - origin.row() == 1;
    }

    private boolean inInitialPositionAndMovedTwoRowsUp()
    {
        return origin.row() == 1 && destination.row() - origin.row() == 2;
    }

    private boolean movedOneDiagonalUp()
    {
        return destination.row() - origin.row() == 1;
    }


    private boolean movedOneRowDown()
    {
        return origin.row() - destination.row() == 1;
    }

    private boolean inInitialPositionAndMovedTwoDown()
    {
        return origin.row() == 6 && origin.row() - destination.row() == 2;
    }

    private boolean movedOneDiagonalDown()
    {
        return origin.row() - destination.row() == 1 && origin.column() - destination.column() == 1;
    }

    private boolean stayedInSameColumn()
    {
        return origin.column() == destination.column();
    }


}