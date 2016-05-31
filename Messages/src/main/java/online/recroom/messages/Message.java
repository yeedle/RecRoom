package online.recroom.messages;

/**
 * Created by Yeedle on 5/30/2016 5:57 PM.
 */
public class Message
{
    public final Class type;
    public final String json;

    public Message(Class type, String json) {
        this.type = type;
        this.json = json;
    }
}
