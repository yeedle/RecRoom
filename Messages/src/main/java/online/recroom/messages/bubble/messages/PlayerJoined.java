package online.recroom.messages.bubble.messages;

import online.recroom.messages.bubble.POJOs.MessageBubblePlayer;

/**
 * Created by Yehuda Globerman on 5/30/2016.
 */
public class PlayerJoined {
    public final MessageBubblePlayer player;

    public PlayerJoined(MessageBubblePlayer player) {
        this.player = player;
    }
}
