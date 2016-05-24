package online.recroom.client.bubbles;

/**
 * Created by Yehuda Globerman on 5/22/2016.
 */
public class Message {
    public final Type type;
    public final online.recroom.client.bubbles.Bubble.ServerBubble[] newBubbles;
    public final long poppedBubbleId;
    public final String winner;
    public final int winnersScore;

    private Message(Type type, Bubble.ServerBubble[]newBubbles, long poppedBubbleId, String winner, int winnersScore) {
        this.type = type;
        this.newBubbles = newBubbles;
        this.poppedBubbleId = poppedBubbleId;
        this.winner = winner;
        this.winnersScore = winnersScore;
    }

    public enum Type {
        GAME_STARTED, BUBBLE_POPPED, GAME_OVER
    }
}
