package online.recroom.server.chess.pieces;

import online.recroom.server.chess.Board;
import online.recroom.server.chess.Coordinates;
import online.recroom.server.chess.IllegalCoordinateException;
import online.recroom.server.chess.Movement;

/**
 * Created by Yeedle on 5/2/2016 2:38 PM.
 */
public class Queen extends Piece
{
    public Queen(Player player)
    {
        super(player);
    }


    @Override
    public boolean isLegalMove(Movement move, Board board) throws IllegalCoordinateException
    {
        Rook rook = new Rook(this.player());
        Bishop bishop = new Bishop(this.player());
        return rook.isLegalMove(move, board) && bishop.isLegalMove(move, board);

    }


}

