package online.recroom.server.chess;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;

/**
 * Created by Yeedle on 4/11/2016 9:07 PM.
 */
@ServerEndpoint(
        value = "/chess/{gameID}/{userID}",
        decoders = {ChessMoveDecoder.class},
        encoders = {ChessMoveEncoder.class})
public class ChessServer
{
    private Session session;
    private Game game;

    @OnOpen
    public void onOpen(Session session)
    {
        //TODO assign Player color to session1
        this.session = session;
        try
        {
            game = new Game();
        }
        catch (IllegalCoordinateException e)
        {
            e.printStackTrace();
        }
    }

    @OnMessage
    public void onMessage(Movement movement)
    {

    }

    @OnError
    public void onError(Throwable throwable)
    {

    }

    @OnClose
    public void onClose()
    {

    }
}
