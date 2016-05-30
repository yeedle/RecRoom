package online.recroom.messages;

import com.google.gson.Gson;

import javax.websocket.EncodeException;
import javax.websocket.Encoder;
import javax.websocket.EndpointConfig;

/**
 * Created by Yeedle on 5/30/2016 6:01 PM.
 */
public class MessageEncoder implements Encoder.Text<Message>
{
    private static Gson gson = new Gson();
    public String encode(Message object) throws EncodeException
    {
        return gson.toJson(object, Message.class);
    }

    public void init(EndpointConfig config)
    {

    }

    public void destroy()
    {

    }
}
