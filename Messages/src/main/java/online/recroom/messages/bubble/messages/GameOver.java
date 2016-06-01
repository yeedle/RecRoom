package online.recroom.messages.bubble.messages;

import online.recroom.messages.bubble.POJOs.MessageBubblePlayer;

/**
 * Created by Yehuda Globerman on 5/30/2016.
 */
public class GameOver {
    public final MessageBubblePlayer winner;
    public final int score;

    public GameOver(MessageBubblePlayer winner, int score) {
        this.winner = winner;
        this.score = score;
    }
}
