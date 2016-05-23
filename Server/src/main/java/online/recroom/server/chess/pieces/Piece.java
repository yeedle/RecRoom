package online.recroom.server.chess.pieces;

import online.recroom.server.chess.Coordinates;
import online.recroom.server.chess.Board;
import online.recroom.server.chess.IllegalCoordinateException;
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



    public Player player()
    {
        return player;
    }

    public void setPlayer(Player player)
    {
        this.player = player;
    }

    public abstract boolean isLegalMove(Movement move, Board board) throws IllegalCoordinateException;

    public boolean isColor(Player color)
    {
        return this.player.equals(color);
    }

    public boolean isNotColorOf(Player color)
    {
        return !isColor(color);
    }

    public <T extends Piece> boolean isInstanceOf(Class<T> type)
    {
        return (type.isInstance(this));
    }

    public <T extends Piece> boolean isNotInstanceOf(Class<T> type)
    {
        return !isInstanceOf(type);
    }

    public  boolean isEmpty()
    {
        return isInstanceOf(Empty.class);
    }

    public  boolean isNotEmpty()
    {
        return !isInstanceOf(Empty.class);
    }
}
