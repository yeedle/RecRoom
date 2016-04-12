package online.recroom.server;

import online.recroom.server.echo.EchoChamberServer;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import javax.websocket.*;

import static org.junit.Assert.*;

/**
 * Created by Yeedle on 4/7/2016 3:39 PM.
 */
public class EchoChamberServerTest {
    private Session session;
    private String stringRecieved;
    @Before
    public void setUp() throws Exception {

    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void echo() throws Exception {
       EchoChamberServer echoChamberServer = new EchoChamberServer();
        String echoedString = echoChamberServer.echo("hello");
        assertEquals("you said: hello", echoedString);
    }



}