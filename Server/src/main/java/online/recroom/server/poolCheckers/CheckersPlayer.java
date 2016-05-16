package online.recroom.server.poolCheckers;

import online.recroom.server.Player;
import online.recroom.server.poolCheckers.pieces.Color;

/**
 * Created by Yehuda Globerman on 5/1/2016.
 */

public class CheckersPlayer extends Player {
    public final Color color;

    public CheckersPlayer(String name, Color color) {
        super(name);
        this.color = color;
    }
}
