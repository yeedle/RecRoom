package online.recroom.server.chess;

import online.recroom.server.chess.pieces.Color;
import online.recroom.server.chess.pieces.Empty;
import online.recroom.server.chess.pieces.Piece;

import java.lang.reflect.Method;
import java.util.Map;

/**
 * Created by Yeedle on 4/11/2016 9:04 PM.
 */
public class Move
{
    private Color madeBy;
    private Class classOfPieceToMove;
    private Coordinates from;
    private Coordinates to;
    private Piece piece;

    public Move(Color madeByPlayer, Coordinates from, Coordinates to)
    {
        madeBy = madeByPlayer;
        this.from = from;
        this.to = to;
    }




    public Color isMadeBy()
    {
        return madeBy;
    }


    public void executeIn(Board board) throws InvalidMove, IllegalMove
    {
        piece = board.pieceInSquare(from.column(), from.row());
        if (piece instanceof Empty)
                throw new InvalidMove("piece not found");
        if (piece.getColor() == madeBy)
            throw new IllegalMove("Occupied by own piece");
        if(piece.isMoveIllegal())
            throw new IllegalMove("Piece can't do that move");

        board.move(from.row(), from.column(), to.row(), to.column());
    }


}
