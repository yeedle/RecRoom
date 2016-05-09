package online.recroom.server.chess.pieces;

import online.recroom.server.chess.Coordinates;
import online.recroom.server.chess.Board;
import online.recroom.server.chess.Movement;

/**
 * Created by Yeedle on 4/28/2016 7:01 PM.
 */
public abstract class Piece
{
    private Player player;


    public Piece(Player player)
    {
       this.player = player;

    }



    public Player getPlayer()
    {
        return player;
    }

    public void setPlayer(Player player)
    {
        this.player = player;
    }

    public abstract boolean isLegalMove(Movement move, Board board);
}
