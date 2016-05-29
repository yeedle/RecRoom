package online.recroom.server.ticTacToe;

import online.recroom.server.ticTacToe.message.Message;

import javax.websocket.EncodeException;
import javax.websocket.Encoder;
import javax.websocket.EndpointConfig;

/**
 * Created by Yeedle on 5/29/2016 5:02 PM.
 */
public class MessageEncoder implements Encoder.Text<Message>
{
    @Override
    public String encode(Message object) throws EncodeException
    {
        return null;
    }

    @Override
    public void init(EndpointConfig config)
    {

    }

    @Override
    public void destroy()
    {

    }
}
