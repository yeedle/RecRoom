package online.recroom.server.chess;

import online.recroom.server.chess.pieces.*;

/**
 * Created by Yeedle on 5/11/2016 12:51 PM.
 */
public class StatusChecker
{
    private Board board;
    private Player player;
    private Coordinates kingsPosition;

    public StatusChecker(Board board)
    {
        this.board = board;
    }

    public boolean isKingInCheck(Player color) throws IllegalCoordinateException
    {
        player = color;
        return isInCheck();
    }

    private boolean isInCheck() throws IllegalCoordinateException
    {
        kingsPosition = board.kingPosition(this.player);
        return isThreatenedDiagonally()
                || isThreatenedVertically()
                || isThreatenedHorizontally()
                || isThreatenedByKnight()
                || isThreatenedByPawn();
    }

    private boolean isThreatenedByKnight() throws IllegalCoordinateException
    {
        if (opponentHasKnightOffsetFromKing(2, 1)
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

    private boolean opponentHasKnightOffsetFromKing(int columnOffset, int rowOffset) throws IllegalCoordinateException
    {
        return opponentHasPieceOffsetFromKing(columnOffset, rowOffset, Knight.class);
    }

    private boolean isThreatenedHorizontally() throws IllegalCoordinateException
    {
        return isThreatenedHorizontallyToTheRight() || isThreatenedHorizontallyToTheLeft();

    }

    private Boolean isThreatenedHorizontallyToTheRight() throws IllegalCoordinateException
    {
        for (int x = kingsPosition.column(), y = kingsPosition.row(); x < Board.COLUMNS; ++x)
        {
            if (isOpponentRookOrQueen(x, y))
                return true;
            if (isNotEmpty(x, y))
                break;
        }
        return false;
    }

    private boolean isThreatenedHorizontallyToTheLeft() throws IllegalCoordinateException
    {
        for (int x = kingsPosition.column(), y = kingsPosition.row(); x > 0; --x)
        {
            if (isOpponentRookOrQueen(x, y))
                return true;
            if (isNotEmpty(x, y))
                break;
        }
        return false;
    }

    private boolean isThreatenedVertically() throws IllegalCoordinateException
    {
        return isThreatenedVerticallyDown() || isThreatenedVerticallyUp();

    }

    private boolean isThreatenedVerticallyUp() throws IllegalCoordinateException
    {
        for (int x = kingsPosition.column(), y = kingsPosition.row(); y < Board.ROWS; ++y)
        {
            if (isOpponentRookOrQueen(x, y))
                return true;
            if (isNotEmpty(x, y))
                break;
        }
        return false;
    }

    private boolean isThreatenedVerticallyDown() throws IllegalCoordinateException
    {

        for (int x = kingsPosition.column(), y = kingsPosition.row(); y > 0; --y)
        {
            if (isOpponentRookOrQueen(x, y))
                return true;
            if (isNotEmpty(x, y))
                break;
        }
        return false;
    }

    private boolean isThreatenedDiagonally() throws IllegalCoordinateException
    {
        for (int x = kingsPosition.column(), y = kingsPosition.row(); x < Board.COLUMNS && y < Board.ROWS; ++x, ++y)
        {
            if (isOpponentBishopOrQueen(x, y))
                return true;
            if (isNotEmpty(x, y))
                break;
        }
        for (int x = kingsPosition.column(), y = kingsPosition.row(); x >= 0 && y >= 0; --x, --y)
        {
            if (isOpponentBishopOrQueen(x, y))
                return true;
            if (isNotEmpty(x, y))
                break;
        }
        return false;
    }

    private boolean isNotEmpty(int x, int y) throws IllegalCoordinateException
    {

        return !isEmpty(x, y);
    }

    private boolean isEmpty(int x, int y) throws IllegalCoordinateException
    {
        Piece currentPiece = board.pieceInSquare(Coordinates.byColumnAndRow(x, y));
        return currentPiece instanceof Empty;
    }

    private boolean isOpponentBishopOrQueen(int x, int y) throws IllegalCoordinateException
    {
        Coordinates coordinates = Coordinates.byColumnAndRow(x, y);
        return (isOpponentPiece(coordinates, Bishop.class) || isOpponentPiece(coordinates, Queen.class));
    }

    private boolean isOpponentRookOrQueen(int x, int y) throws IllegalCoordinateException
    {
        Coordinates coordinates = Coordinates.byColumnAndRow(x, y);
        return (isOpponentPiece(coordinates, Rook.class) || isOpponentPiece(coordinates, Queen.class));
    }

    private <T extends Piece> boolean
    isOpponentPiece(Coordinates coordinates, Class<T> theClass)
    {
        Piece pieceInQuestion = board.pieceInSquare(coordinates);
        return pieceInQuestion.isInstanceOf(theClass) && pieceInQuestion.isNotColorOf(this.player);
    }

      private boolean isThreatenedByPawn() throws IllegalCoordinateException
    {
        return opponentHasPawnOffsetFromKing(colOffsetBasedOnPlayer(), 1)
                || opponentHasPawnOffsetFromKing(colOffsetBasedOnPlayer(), -1) ? true :false;
    }

    private int colOffsetBasedOnPlayer()
    {
        return this.player.equals(Player.WHITE) ? 1 : -1;
    }

    private boolean opponentHasPawnOffsetFromKing(int colOffset, int rowOffset) throws IllegalCoordinateException
    {
        return opponentHasPieceOffsetFromKing(colOffset, rowOffset, Pawn.class);
    }

    private <T extends Piece> boolean opponentHasPieceOffsetFromKing(int colOffset, int rowOffset, Class<T> type) throws IllegalCoordinateException
    {
        if (coordinatesAreOutOfBounds(colOffset, rowOffset))
            return false;
        Piece piece = board.pieceInSquare(Coordinates.byColumnAndRow(kingsPosition.column()+colOffset, kingsPosition.row()+rowOffset));
        if (piece.isNotInstanceOf(type))
            return false;
        return piece.isNotColorOf(this.player);
    }

    private boolean coordinatesAreOutOfBounds(int colOffset, int rowOffset)
    {
        return kingsPosition.column()+colOffset > Board.COLUMNS
                || kingsPosition.column()+colOffset < 0
                || kingsPosition.row()+rowOffset > Board.ROWS
                || kingsPosition.row()+rowOffset < 0;
    }
}