package online.recroom.client.chess;

import javafx.scene.layout.StackPane;
import online.recroom.client.chess.pieces.Coordinate;

/**
 * Created by Yeedle on 5/29/2016 2:14 PM.
 */
public class Square extends StackPane
{
    private Coordinate coordinate;
    public void setCoordinate(Coordinate coordinate)
    {
        this.coordinate = coordinate;
    }

    public Coordinate getCoordinate()
    {
        return coordinate;
    }
}
