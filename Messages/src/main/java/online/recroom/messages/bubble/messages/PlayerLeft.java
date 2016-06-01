package online.recroom.messages.bubble.messages;

import online.recroom.messages.bubble.POJOs.MessageBubblePlayer;

/**
 * Created by Yehuda Globerman on 5/30/2016.
 */
public class PlayerLeft {
    public final MessageBubblePlayer player;

    public PlayerLeft(MessageBubblePlayer player) {
        this.player = player;
    }
}
