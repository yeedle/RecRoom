package online.recroom.messages.bubble.messages;

import online.recroom.messages.bubble.POJOs.BubblePlayer;

/**
 * Created by Yehuda Globerman on 5/30/2016.
 */
public class GameOver {
    public final BubblePlayer winner;
    public final int score;

    public GameOver(BubblePlayer winner, int score) {
        this.winner = winner;
        this.score = score;
    }
}
