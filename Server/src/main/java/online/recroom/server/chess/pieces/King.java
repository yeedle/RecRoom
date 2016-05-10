package online.recroom.server.chess.pieces;

import online.recroom.server.chess.Coordinates;
import online.recroom.server.chess.Board;
import online.recroom.server.chess.IllegalCoordinateException;
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
        return movedOneHorizontally() || movedOneDiagonally() || movedOneVertically();

    }

    private boolean movedOneHorizontally()
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

    public boolean isInCheck(Board board) throws IllegalCoordinateException
    {
        this.board = board;
        kingsPosition = board.kingPosition(this.getPlayer());
        return isThreatenedDiagonally()
                || isThreatenedVertically()
                || isThreatenedHorizontally()
                || isThreatenedByKnight()
                || isThreatenedByPawn();
    }

    private boolean isThreatenedByKnight()
    {
        if (   opponentHasKnightOffsetFromKing(2, 1)
            || opponentHasKnightOffsetFromKing(2, -1)
            || opponentHasKnightOffsetFromKing(1, 2)
            || opponentHasKnightOffsetFromKing(1, -2)
            || opponentHasKnightOffsetFromKing(-2, 1)
            || opponentHasKnightOffsetFromKing(-2, -1)
            || opponentHasKnightOffsetFromKing(-1, 2)
            || opponentHasKnightOffsetFromKing(-1, -2))
            return true;

        return false;
    }

    private boolean opponentHasKnightOffsetFromKing(int columnOffset, int rowOffset)
    {
        return opponentHasPieceOffsetFromKing(columnOffset, rowOffset, Knight.class);
    }

    private boolean isThreatenedHorizontally()
    {
        return true;
    }

    private boolean isThreatenedVertically()
    {
        return true;
    }

    private boolean isThreatenedDiagonally() throws IllegalCoordinateException
    {

        for (int x = kingsPosition.column(), y = kingsPosition.row(); x < Board.COLUMNS && y < Board.ROWS; ++x, ++y)
        {
            Piece currentPiece = board.pieceInSquare(Coordinates.byColumnAndRow(x, y));
                if (currentPiece instanceof Empty)
                    continue;
                if ((currentPiece instanceof Bishop || currentPiece instanceof Queen) && !currentPiece.getPlayer().equals(this.getPlayer()))
                    return true;
        }
        for (int x = kingsPosition.column(), y = kingsPosition.row(); x > 0 && y < 0; --x, --y)
        {
            Piece currentPiece = board.pieceInSquare(Coordinates.byColumnAndRow(x, y));
                if (currentPiece instanceof Empty)
                    continue;
                if ((currentPiece instanceof Bishop || currentPiece instanceof  Queen) && !currentPiece.getPlayer().equals(this.getPlayer()))
                    return true;
        }
        return false;
    }

    private boolean isThreatenedByPawn()
    {
        int col = kingsPosition.column()+ (getPlayer().equals(Player.WHITE) ? 1 : -1);
        if (opponentHasPawnOffsetFromKing(col, 1)
                || opponentHasPawnOffsetFromKing(col, -1))
            return true;
        return false;
    }

    private boolean opponentHasPawnOffsetFromKing(int colOffset, int rowOffset)
    {
        return opponentHasPieceOffsetFromKing(colOffset, rowOffset, Pawn.class);
    }

    private <T extends Piece> boolean opponentHasPieceOffsetFromKing(int colOffset, int rowOffset, Class<T> theClass)
    {

        try
        {
            T piece = theClass.cast(board.pieceInSquare(Coordinates.byColumnAndRow(colOffset, kingsPosition.row()+rowOffset)));
            return piece.isNotColor(this.getPlayer());
        }
        catch (IllegalCoordinateException | ClassCastException e){ return false;}
    }


}
