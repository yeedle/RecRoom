package online.recroom.server.checkers;

import online.recroom.server.checkers.board.Color;

/**
 * Created by Yehuda Globerman on 5/1/2016.
 */
public class Player {
    private final String firstName;
    private final String lastName;
    private Color color;

    public Player(String fName, String lName)
    {
        firstName = fName;
        lastName = lName;
    }
}
