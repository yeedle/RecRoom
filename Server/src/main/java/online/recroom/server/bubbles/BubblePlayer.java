package online.recroom.server.bubbles;

import online.recroom.server.Player;

/**
 * Created by Yehuda Globerman on 5/22/2016.
 */
public class BubblePlayer extends Player implements Comparable<BubblePlayer> {
    private int bubblesPopped;

    public BubblePlayer(String name) {
        super(name);
        bubblesPopped = 0;
    }

    public int getScore() {
        return bubblesPopped;
    }

    public void incrementBubblesPopped() {
        bubblesPopped++;
    }


    @Override
    public int compareTo(BubblePlayer otherPlayer) {
        return this.bubblesPopped - otherPlayer.bubblesPopped;
    }
}
