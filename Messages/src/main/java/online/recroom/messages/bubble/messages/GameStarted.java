package online.recroom.messages.bubble.messages;


import online.recroom.messages.bubble.POJOs.MessageBubble;
import online.recroom.messages.bubble.POJOs.MessageBubblePlayer;

/**
 * Created by Yehuda Globerman on 5/30/2016.
 */
public class GameStarted {
    public final MessageBubble[] bubbles;
    public final MessageBubblePlayer[] players;
    public final boolean joinedActiveGame;

    public GameStarted(MessageBubble[] bubbles, MessageBubblePlayer[] players, boolean joinedActiveGame) {
        this.bubbles = bubbles;
        this.players = players;
        this.joinedActiveGame = joinedActiveGame;
    }
}
