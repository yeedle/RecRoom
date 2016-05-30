package online.recroom.messages;

import com.google.gson.Gson;

import javax.websocket.DecodeException;
import javax.websocket.Decoder;
import javax.websocket.EndpointConfig;

/**
 * Created by Yeedle on 5/30/2016 6:00 PM.
 */
public class MessageDecoder implements Decoder.Text<Message>
{
    public Message decode(String s) throws DecodeException
    {
        return null;
    }

    public boolean willDecode(String s)
    {
        return false;
    }

    public void init(EndpointConfig config)
    {

    }

    public void destroy()
    {

    }
}
