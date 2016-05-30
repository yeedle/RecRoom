package online.recroom.messages.bubble.messages;


import online.recroom.messages.bubble.POJOs.Bubble;
import online.recroom.messages.bubble.POJOs.BubblePlayer;

/**
 * Created by Yehuda Globerman on 5/30/2016.
 */
public class GameStarted {
    public final Bubble[] bubbles;
    public final BubblePlayer[] players;

    public GameStarted(Bubble[] bubbles, BubblePlayer[] players) {
        this.bubbles = bubbles;
        this.players = players;
    }
}
