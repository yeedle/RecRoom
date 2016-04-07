package online.recroom.server;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

/**
 * Created by Yeedle on 3/22/2016 8:40 PM.
 */
@ServerEndpoint("/game/{gameId}/{username}")
public class RecRoomServer
{

    @OnOpen
    public void onOpen(Session session, @PathParam("gameId") long gameId,
                       @PathParam("username") String username)
    {

    }

    @OnMessage
    public void OnMessage(Session session, String message, @PathParam("gameId") long gameId)
    {

    }

    @OnClose
    public void onClose(Session session, @PathParam("gameId") long gameId)
    {

    }



}
