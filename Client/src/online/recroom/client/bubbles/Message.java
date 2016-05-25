package online.recroom.client.bubbles;

/**
 * Created by Yehuda Globerman on 5/22/2016.
 */
public class Message {
    public final Type type;
    public final online.recroom.client.bubbles.Bubble.ServerBubble[] newBubbles;
    public final BubblePlayer[] players;
    public final long poppedBubbleId;
    public final String winner;
    public final int winnersScore;
    public final String playerName;

    private Message(Type type, Bubble.ServerBubble[] newBubbles, BubblePlayer[] players, long poppedBubbleId, String winner, int winnersScore, String playerName) {
        this.type = type;
        this.newBubbles = newBubbles;
        this.players = players;
        this.poppedBubbleId = poppedBubbleId;
        this.winner = winner;
        this.winnersScore = winnersScore;
        this.playerName = playerName;
    }

    public enum Type {
        GAME_STARTED, GAME_PENDING, JOINED_GAME, PLAYER_JOINED, PLAYER_LEFT, BUBBLE_POPPED, GAME_OVER
    }
}
