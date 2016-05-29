package online.recroom.client.chess;

import online.recroom.client.chess.pieces.Coordinate;

import javax.websocket.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

/**
 * Created by Yeedle on 5/25/2016 7:58 PM.
 */
@ClientEndpoint
public class Endpoint
{
    final String WebSocketURI;
    private Session session;
    private Controller controller;
    private String username;

    public Endpoint(String username, Controller controller)
    {
        this.username = username.isEmpty() ? "Anonymous" : username;
        WebSocketURI = "ws://localhost:8080/recroom/bubble?username=" + this.username;
        this.controller = controller;
    }

    @OnOpen
    public void onOpen(Session session)
    {
        this.session = session;
        controller.setEndpoint(this);
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

    public void sendMessage(Coordinate origin, Coordinate destination)
    {
        try
        {
            //todo construct message to send to server
            session.getBasicRemote().sendText(origin.toString());
        } catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public void connect() throws URISyntaxException, IOException, DeploymentException
    {
        WebSocketContainer container = ContainerProvider.getWebSocketContainer();
        container.connectToServer(this, new URI(WebSocketURI));
    }
}
