package online.recroom.server;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Yeedle on 3/22/2016 8:40 PM.
 */

@ServerEndpoint("/game/{gameId}/{username}")
public class RecRoomServer
{
    protected static Set<Session> sessions = Collections.synchronizedSet(new HashSet<Session>());

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
