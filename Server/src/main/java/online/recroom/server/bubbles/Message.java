package online.recroom.server.bubbles;

/**
 * Created by Yehuda Globerman on 5/22/2016.
 */
public class Message {
    public final Type type;
    public final Bubble[] newBubbles;
    public final Long poppedBubbleId;
    public final String winner;
    public final Integer winnersScore;

    private Message(Type type, Bubble[] newBubble, Long poppedBubbleId, String winner, Integer winnersScore) {
        this.type = type;
        this.newBubbles = newBubble;
        this.poppedBubbleId = poppedBubbleId;
        this.winner = winner;
        this.winnersScore = winnersScore;
    }

    public enum Type {
        GAME_STARTED, BUBBLE_POPPED, GAME_OVER
    }

    public static Message createGameStartedMessage(Bubble[] newBubble) {
        return new Message(Type.GAME_STARTED, newBubble, null, null, null);
    }

    public static Message createBubblePoppedMessage(Long poppedBubbleId) {
        return new Message(Type.BUBBLE_POPPED, null, poppedBubbleId, null, null);
    }

    public static Message createGameOverMessage(String winner, Integer winnersScore) {
        return new Message(Type.GAME_OVER, null, null, winner, winnersScore);
    }
}
