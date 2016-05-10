package online.recroom.server.chess.pieces;

import online.recroom.server.chess.Coordinates;
import online.recroom.server.chess.Board;
import online.recroom.server.chess.Movement;

/**
 * Created by Yeedle on 5/2/2016 2:39 PM.
 */
public class King extends Piece
{

    private Coordinates origin;
    private Coordinates destination;
    private Coordinates kingsPosition;
    private Board board;

    public King(Player player)
    {
        super(player);
    }

    @Override
    public boolean isLegalMove(Movement move, Board board)
    {
        origin = move.origin;
        destination = move.destination;
        return MovedOneHorizontally() || movedOneDiagonally() || movedOneVertically();

    }

    private boolean MovedOneHorizontally()
    {
       return (Math.abs(origin.column() - destination.column()) == 1)
               && origin.row() == destination.row();
    }

    private boolean movedOneVertically()
    {
        return Math.abs(origin.row() - destination.row()) == 1
                && origin.column() == destination.column();
    }

    private boolean movedOneDiagonally()
    {
        return (Math.abs(origin.column() - destination.column()) ==1
                && (Math.abs(origin.row() - destination.row()) ==1));
    }

    public boolean isNotInCheck(Board board)
    {
        this.board = board;
        kingsPosition = board.kingPosition(this.getPlayer());
        return noDiagonalThreats() && noHorizontalThreats() &&
        noVerticalThreats() &&
        noKnightThreats();
        return false;
    }

    public boolean noDiagonalThreats()
    {
        for (int x = kingsPosition.column(), y = kingsPosition.row(); x < Board.COLUMNS || y < Board.ROWS; x++, y++)
        {

        }
    }


}
