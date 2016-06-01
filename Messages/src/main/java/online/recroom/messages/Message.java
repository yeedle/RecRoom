package online.recroom.messages;

import online.recroom.messages.bubble.enums.BubbleMessages;

/**
 * Created by Yeedle on 5/30/2016 5:57 PM.
 */
public class Message
{
    public final BubbleMessages type;
    public final String json;

    public Message(BubbleMessages type, String json) {
        this.type = type;
        this.json = json;
    }
}
