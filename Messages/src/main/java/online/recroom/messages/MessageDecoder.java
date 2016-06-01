package online.recroom.messages;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import javax.websocket.DecodeException;
import javax.websocket.Decoder;
import javax.websocket.EndpointConfig;

/**
 * Created by Yeedle on 5/30/2016 6:00 PM.
 */
public class MessageDecoder implements Decoder.Text<Message>
{
    private static Gson gson = new Gson();
    public Message decode(String s) throws DecodeException
    {
        if (!willDecode(s))
            throw new JsonSyntaxException("Garbage data");
        return gson.fromJson(s, Message.class);
    }

    public boolean willDecode(String s)
    {
        try {
            gson.fromJson(s, Message.class);
            return true;
        } catch (JsonSyntaxException e) {
            return false;
        }
    }

    public void init(EndpointConfig config)
    {

    }

    public void destroy()
    {

    }
}
