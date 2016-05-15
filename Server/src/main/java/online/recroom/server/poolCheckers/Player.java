package online.recroom.server.poolCheckers;

import online.recroom.server.poolCheckers.pieces.Color;

/**
 * Created by Yehuda Globerman on 5/1/2016.
 */

public class Player {
    public final String name;
    public final Color color;

    public Player(String fName, String lName, Color color)
    {
        name = fName;
        this.color = color;
    }
}
