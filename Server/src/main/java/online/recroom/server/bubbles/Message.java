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
    public final String playerName;

    private Message(Type type, Bubble[] newBubble, BubblePlayer[] players, Long poppedBubbleId, String winner, Integer winnersScore, String playerName) {
        this.type = type;
        this.newBubbles = newBubble;
        this.players = players;
        this.poppedBubbleId = poppedBubbleId;
        this.winner = winner;
        this.winnersScore = winnersScore;
        this.playerName = playerName;
    }

    public enum Type {
        GAME_STARTED, GAME_PENDING, JOINED_GAME, PLAYER_JOINED, PLAYER_LEFT, BUBBLE_POPPED, GAME_OVER
    }

    public static Message gameStarted(Bubble[] newBubble, BubblePlayer[] players) {
        return new Message(Type.GAME_STARTED, newBubble, players, null, null, null, null);
    }

    public static Message gamePending() {
        return new Message(Type.GAME_PENDING, null, null, null, null, null, null);
    }

    public static Message joinedGame(Bubble[] bubbles, BubblePlayer[] players) {
        return new Message(Type.JOINED_GAME, bubbles, players, null, null, null, null);
    }

    public static Message bubblePopped(Long poppedBubbleId) {
        return new Message(Type.BUBBLE_POPPED, null, null, poppedBubbleId, null, null, null);
    }

    public static Message gameOver(String winner, Integer winnersScore) {
        return new Message(Type.GAME_OVER, null, null, null, winner, winnersScore, null);
    }

    public static Message playerJoined(String playerName) {
        return new Message(Type.PLAYER_JOINED, null, null, null, null, null, playerName);
    }

    public static Message playerLeft(String playerName) {
        return new Message(Type.PLAYER_LEFT, null, null, null, null, null, playerName);
    }
}
