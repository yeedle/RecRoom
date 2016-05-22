package online.recroom.server.bubbles;

import online.recroom.server.Player;

/**
 * Created by Yehuda Globerman on 5/22/2016.
 */
public class BubblePlayer extends Player {
    private int bubblesPopped;

    public BubblePlayer(String name) {
        super(name);
        bubblesPopped = 0;
    }

    public int getBubblesPopped() {
        return bubblesPopped;
    }

    public void incerementBubblesPopped() {
        bubblesPopped++;
    }
}
