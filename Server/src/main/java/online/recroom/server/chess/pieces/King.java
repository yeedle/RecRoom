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
        return isThreatenedDiagonaly()
                || isThreatenedVertically()
                || isThreatenedHorizontally()
                || isThreatenedByKnight();
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
        try
        {
            Knight knight = (Knight) board.pieceInSquare(Coordinates.byColumnAndRow(kingsPosition.column()+columnOffset, kingsPosition.row()+rowOffset));
            return knight.isNotColor(this.getPlayer());
        }
        catch (IllegalCoordinateException | ClassCastException e) {return false; }
    }

    private boolean isThreatenedHorizontally()
    {
        return true;
    }

    private boolean isThreatenedVertically()
    {
        return true;
    }

    private boolean isThreatenedDiagonaly() throws IllegalCoordinateException
    {
        if (getPlayer().equals(Player.WHITE))
        {
            try
            {
                int col = kingsPosition.column()+1;
                int row = kingsPosition.row()+1;
                Coordinates co = Coordinates.byColumnAndRow(col, row);
                Piece piece = board.pieceInSquare(co);
                Piece piece1 = board.pieceInSquare(Coordinates.byColumnAndRow(kingsPosition.column()+1, kingsPosition.row()-1));
                if ((piece instanceof Pawn && piece.getPlayer().equals(Player.BLACK)) || piece1 instanceof  Pawn && piece1.getPlayer().equals(Player.BLACK))
                    return false;
            }
            catch (IllegalCoordinateException e)
            {

            }

        }

        if (getPlayer().equals(Player.BLACK))
        {
            try
            {
                Piece piece = board.pieceInSquare(Coordinates.byColumnAndRow(kingsPosition.column()-1, kingsPosition.row()+1));
                Piece piece1 = board.pieceInSquare(Coordinates.byColumnAndRow(kingsPosition.column()-1, kingsPosition.row()-1));
                if ((piece instanceof Pawn && piece.getPlayer().equals(Player.WHITE)) || piece1 instanceof  Pawn && piece1.getPlayer().equals(Player.WHITE))
                    return false;
            }
            catch (IllegalCoordinateException e)
            {

            }

        }

        for (int x = kingsPosition.column(), y = kingsPosition.row(); x < Board.COLUMNS && y < Board.ROWS; ++x, ++y)
        {
            Piece currentPiece = board.pieceInSquare(Coordinates.byColumnAndRow(x, y));
                if (currentPiece instanceof Empty)
                    continue;
                if ((currentPiece instanceof Rook || currentPiece instanceof Queen) && !currentPiece.getPlayer().equals(this.getPlayer()))
                    return false;
        }
        for (int x = kingsPosition.column(), y = kingsPosition.row(); x > 0 && y < 0; --x, --y)
        {
            Piece currentPiece = board.pieceInSquare(Coordinates.byColumnAndRow(x, y));
                if (currentPiece instanceof Empty)
                    continue;
                if ((currentPiece instanceof Rook || currentPiece instanceof  Queen) && !currentPiece.getPlayer().equals(this.getPlayer()))
                    return false;
        }
        return true;
    }


}
