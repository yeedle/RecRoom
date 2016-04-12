package online.recroom.server.chess;

import javax.websocket.EncodeException;
import javax.websocket.Encoder;
import javax.websocket.EndpointConfig;

/**
 * Created by Yeedle on 4/11/2016 8:57 PM.
 */
public class ChessMoveEncoder implements Encoder.Text<Move>
{
    @Override
    public void init(EndpointConfig endpointConfig)
    {

    }

    @Override
    public void destroy()
    {

    }

    @Override
    public String encode(Move t) throws EncodeException
    {
        //In this method, the JSON gets built
        return null;
    }


}
