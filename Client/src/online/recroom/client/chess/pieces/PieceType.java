package online.recroom.client.chess.pieces;

/**
 * Created by Yeedle on 5/23/2016 12:48 PM.
 */
public enum PieceType
{
    ROOK, PAWN, KING, QUEEN, KNIGHT, BISHOP;

    @Override
    public String toString()
    {
        return super.toString().toLowerCase();
    }
}
