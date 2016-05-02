package online.recroom.server.chess.pieces;

import java.util.Map;

/**
 * Created by Yeedle on 4/28/2016 7:01 PM.
 */
public class Piece
{
    private Color color;

    public Piece(Color color)
    {
       this.color = color;
    }

    public Color getColor()
    {
        return color;
    }

    public void setColor(Color color)
    {
        this.color = color;
    }
}
