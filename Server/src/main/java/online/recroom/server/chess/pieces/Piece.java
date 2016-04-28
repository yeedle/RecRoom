package online.recroom.server.chess.pieces;

import java.util.Map;

/**
 * Created by Yeedle on 4/28/2016 7:01 PM.
 */
public class Piece
{
    private Map<Dimension, Integer> position;
    private Color color;


    public void setPosition(int row, int column)
    {
        position.put(Dimension.ROW, row);
        position.put(Dimension.COLUMN, column);
    }
}
