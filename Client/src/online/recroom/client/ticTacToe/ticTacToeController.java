package online.recroom.client.ticTacToe;

import javax.websocket.*;
import java.io.IOException;
import java.net.URI;

/**
 * Created by Yeedle on 5/19/2016 5:24 PM.
 */
@ClientEndpoint
public class ticTacToeController
{
    private Session session;

    @OnOpen
    public void onOpen(final Session session) {this.session = session; }

    @OnMessage
    public void onMessage(final String string){
        System.out.println("incoming msg: " + string);
        //TODO: Create a decoder to handle incoming POJOs.
    }

    public void sendMessage(final String o)
    {
        try
        {
            session.getBasicRemote().sendText(o);
        } catch (IOException e)

        {
            e.printStackTrace();
        }
    }

    @OnError
    public void onError(final Session session, Throwable throwable)
    {
        System.out.println(throwable.toString());
    }


    @OnClose
    public void onClose(final Session session, final CloseReason reason)
    {
        System.out.println(reason.getReasonPhrase());
        this.session = null;
    }




    private void connectToWebSocket(final URI uri)
    {
        WebSocketContainer container = ContainerProvider.getWebSocketContainer();
        try
        {
            container.connectToServer(this, uri);
        } catch (Exception e)
        {
            e.printStackTrace();
        }

    }
}
