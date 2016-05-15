package online.recroom.server.poolCheckers;

import online.recroom.server.poolCheckers.pieces.Color;

/**
 * Created by Yehuda Globerman on 5/1/2016.
 */

public class Player {
    public final String name;
    public final Color color;

    public Player(String name, Color color)
    {
        this.name = name;
        this.color = color;
    }
}
