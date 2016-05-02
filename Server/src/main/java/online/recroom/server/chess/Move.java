package online.recroom.server.chess;

import online.recroom.server.chess.pieces.Color;

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

    public Move(Class classOfPiece, Color madeByPlayer, Coordinates from, Coordinates to)
    {
        madeBy = madeByPlayer;
        classOfPieceToMove = classOfPiece;
        this.from = from;
        this.to = to;
    }

    public Color isMadeBy()
    {
        return madeBy;
    }

    public Class getClassOfPieceToMove()
    {
        return classOfPieceToMove;
    }

    public int getFromColumn()
    {
        return from.column();
    }

    public int getFromRow()
    {
        return from.row();
    }

    public int getToColumn()
    {
        return to.column();
    }

    public int getToRow()
    {
        return to.row();
    }
}
