package online.recroom.server.bubbles;

/**
 * Created by Yehuda Globerman on 5/22/2016.
 */
public class Message {
    public final Type type;
    public final Bubble[] newBubble;
    public final long poppedBubbleId;
    public final String winner;
    public final int winnersScore;

    public Message(Type type, Bubble[] newBubble, long poppedBubbleId, String winner, int winnersScore) {
        this.type = type;
        this.newBubble = newBubble;
        this.poppedBubbleId = poppedBubbleId;
        this.winner = winner;
        this.winnersScore = winnersScore;
    }

    public enum Type {
        GAME_STARTED, BUBBLE_POPPED, GAME_OVER
    }
}
