package online.recroom.server.ticTacToe;

import javax.websocket.DecodeException;
import javax.websocket.Decoder;
import javax.websocket.EndpointConfig;

/**
 * Created by Yeedle on 5/29/2016 5:00 PM.
 */
public class MessageDecoder implements Decoder.Text<Move>
{

    @Override
    public Move decode(String s) throws DecodeException
    {
        return null;
    }

    @Override
    public boolean willDecode(String s)
    {
        return false;
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
