package online.recroom.messages.bubble.messages;

import online.recroom.messages.bubble.POJOs.BubblePlayer;

/**
 * Created by Yehuda Globerman on 5/30/2016.
 */
public class PlayerLeft {
    public final BubblePlayer player;

    public PlayerLeft(BubblePlayer player) {
        this.player = player;
    }
}
