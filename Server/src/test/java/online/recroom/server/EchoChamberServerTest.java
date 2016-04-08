package online.recroom.server;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import javax.websocket.*;

import java.io.IOException;
import java.net.URI;

import static org.junit.Assert.*;

/**
 * Created by Yeedle on 4/7/2016 3:39 PM.
 */
public class EchoChamberServerTest extends Endpoint {
    private Session session;
    private String stringRecieved;
    @Before
    public void setUp() throws Exception {
        WebSocketContainer ws = ContainerProvider.getWebSocketContainer();
        ws.connectToServer(this, new URI("ws://localhost:8080/recroom/echo"));
    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void echo() throws Exception {
        session.getBasicRemote().sendText("hello");
        assertEquals("hello", stringRecieved);
    }

    @Override
    public void onOpen(Session session, EndpointConfig endpointConfig) {
        this.session = session;
        session.addMessageHandler(new MessageHandler.Whole<String>() {
            @Override
            public void onMessage(String s) {
                stringRecieved = s;
            }
        });
    }


}