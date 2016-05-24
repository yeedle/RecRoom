package online.recroom.server.bubbles;

/**
 * Created by Yehuda Globerman on 5/22/2016.
 */
public class Message {
    public final Type type;
    public final Bubble[] newBubbles;
    public final BubblePlayer[] players;
    public final Long poppedBubbleId;
    public final String winner;
    public final Integer winnersScore;

    private Message(Type type, Bubble[] newBubble, BubblePlayer[] players, Long poppedBubbleId, String winner, Integer winnersScore) {
        this.type = type;
        this.newBubbles = newBubble;
        this.players = players;
        this.poppedBubbleId = poppedBubbleId;
        this.winner = winner;
        this.winnersScore = winnersScore;
    }

    public enum Type {
        GAME_STARTED, GAME_PENDING, JOINED_GAME, PLAYER_JOINED, BUBBLE_POPPED, GAME_OVER
    }

    public static Message createGameStartedMessage(Bubble[] newBubble, BubblePlayer[] players) {
        return new Message(Type.GAME_STARTED, newBubble, players, null, null, null);
    }

    public static Message createGamePendingMessage() {
        return new Message(Type.GAME_PENDING, null, null, null, null, null);
    }

    public static Message createJoinedGameMessage(Bubble[] bubbles, BubblePlayer[] players) {
        return new Message(Type.JOINED_GAME, bubbles, players, null, null, null);
    }

    public static Message createBubblePoppedMessage(Long poppedBubbleId) {
        return new Message(Type.BUBBLE_POPPED, null, null, poppedBubbleId, null, null);
    }

    public static Message createGameOverMessage(String winner, Integer winnersScore) {
        return new Message(Type.GAME_OVER, null, null, null, winner, winnersScore);
    }
}
