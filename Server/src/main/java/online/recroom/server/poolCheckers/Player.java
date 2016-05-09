package online.recroom.server.poolCheckers;

import online.recroom.server.poolCheckers.pieces.Color;

/**
 * Created by Yehuda Globerman on 5/1/2016.
 */

public class Player {
    private final String firstName;
    private final String lastName;
    private final Color color;

    public Player(String fName, String lName, Color color)
    {
        firstName = fName;
        lastName = lName;
        this.color = color;
    }
}
