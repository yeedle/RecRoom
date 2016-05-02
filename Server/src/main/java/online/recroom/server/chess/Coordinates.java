package online.recroom.server.chess;

import online.recroom.server.chess.pieces.Dimension;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by Yeedle on 5/2/2016 3:27 PM.
 */
public class Coordinates
{
    Map<Dimension, Integer> coordinatesMap = new ConcurrentHashMap<>();

    private Coordinates(int column, int row)
    {
        coordinatesMap.put(Dimension.COLUMN, column);
        coordinatesMap.put(Dimension.ROW, row);
    }

    public static Coordinates byColumnAndRow(int column, int row)
    {
         return new Coordinates(column, row);
    }

    public int column()
    {
        return coordinatesMap.get(Dimension.COLUMN);
    }

    public int row()
    {
        return coordinatesMap.get(Dimension.ROW);
    }
}
