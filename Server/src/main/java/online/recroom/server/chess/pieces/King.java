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

    public boolean isNotInCheck(Board board)
    {
        this.board = board;
        kingsPosition = board.kingPosition(this.getPlayer());
        return noDiagonalThreats()
                && noHorizontalThreats()
                && noVerticalThreats()
                && noKnightThreats();
    }

    private boolean noKnightThreats()
    {
        return false;
    }

    private boolean noVerticalThreats()
    {
        return false;
    }

    private boolean noHorizontalThreats()
    {
        return false;
    }

    public boolean noDiagonalThreats()
    {
        if (getPlayer().equals(Player.WHITE))
        {
            Piece piece = board.pieceInSquare(kingsPosition.column()+1, kingsPosition.row()+1);
            Piece piece1 = board.pieceInSquare(kingsPosition.column()+1, kingsPosition.row()-1);
            if ((piece instanceof Pawn && piece.getPlayer().equals(Player.BLACK)) || piece1 instanceof  Pawn && piece1.getPlayer().equals(Player.BLACK))
                return false;
        }

        if (getPlayer().equals(Player.BLACK))
        {
            Piece piece = board.pieceInSquare(kingsPosition.column()-1, kingsPosition.row()+1);
            Piece piece1 = board.pieceInSquare(kingsPosition.column()-1, kingsPosition.row()-1);
            if ((piece instanceof Pawn && piece.getPlayer().equals(Player.WHITE)) || piece1 instanceof  Pawn && piece1.getPlayer().equals(Player.WHITE))
                return false;
        }

        for (int x = kingsPosition.column(), y = kingsPosition.row(); x < Board.COLUMNS && y < Board.ROWS; ++x, ++y)
        {
            Piece currentPiece = board.pieceInSquare(x, y);
                if (currentPiece instanceof Empty)
                    continue;
                if ((currentPiece instanceof Rook || currentPiece instanceof Queen) && !currentPiece.getPlayer().equals(this.getPlayer()))
                    return false;
        }
        for (int x = kingsPosition.column(), y = kingsPosition.row(); x > 0 && y < 0; --x, --y)
        {
            Piece currentPiece = board.pieceInSquare(x, y);
                if (currentPiece instanceof Empty)
                    continue;
                if ((currentPiece instanceof Rook || currentPiece instanceof  Queen) && !currentPiece.getPlayer().equals(this.getPlayer()))
                    return false;
        }
        return true;
    }


}
