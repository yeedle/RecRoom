package online.recroom.client.chess;

import javax.websocket.*;

/**
 * Created by Yeedle on 5/25/2016 7:58 PM.
 */
@ClientEndpoint
public class Endpoint
{
    private Session session;
    private Controller controller;
    private String username;

    public Endpoint(String username, Controller controller)
    {
        this.username = username.isEmpty() ? "Anonymous" : username;
        this.controller = controller;
    }

    @OnOpen
    public void onOpen(Session session)
    {
        this.session = session;
    }

    @OnMessage
    public void onMessage(String string)
    {

    }

    @OnError
    public void onError(Throwable t)
    {
        t.printStackTrace();
    }

    @OnClose
    public void onClose(CloseReason closeReason)
    {

    }
}
