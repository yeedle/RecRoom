package online.recroom.server;

import javax.websocket.OnMessage;
import javax.websocket.server.ServerEndpoint;

/**
 * Created by Yeedle on 4/7/2016 11:51 AM.
 */
@ServerEndpoint("/echo")
public class EchoChamberServer
{
    @OnMessage
    public String echo(String message)
    {
        return "you said: " + message;
    }
}
