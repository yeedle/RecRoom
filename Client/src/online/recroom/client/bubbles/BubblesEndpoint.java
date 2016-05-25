package online.recroom.client.bubbles;

import javafx.animation.*;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.media.Media;

import javax.websocket.*;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by Yeedle on 5/17/2016 9:32 AM.
 */
@ClientEndpoint (decoders = MessageDecoder.class)
public class BubblesEndpoint
{
    private Session session;
    private BubblesController controller;
    private String username = "Anonymous";
    private String  WebSocketURI = "ws://localhost:8080/recroom/bubble?username=" + username;

    public BubblesEndpoint(String username, BubblesController controller)
    {
        this.username = username;
        this.controller = controller;
    }

    @OnOpen
    public void onOpen(final Session session)
    {
        this.session = session;
        controller.setEndpoint(this);
    }

    @OnMessage
    public void onMessage(final Message message) throws IOException
    {
        System.out.println("message received");
        switch (message.type)
        {
            case GAME_PENDING:
                System.out.println("waiting for another player to join");
                break;
            case JOINED_GAME:
            {
                System.out.println("someone joined your game");
                gameStarted(message.newBubbles);
                break;
            }
                case GAME_STARTED:
            {
                System.out.println("game started");
                gameStarted(message.newBubbles);
                break;
            }
            case BUBBLE_POPPED:
            {
                System.out.println("a bubble was popped");
                bubblePopped(message.poppedBubbleId);
                break;
            }
            case GAME_OVER:
                gameOver(message.winner, message.winnersScore);
                break;
            default:
                System.out.println("default");
                break;
        }
    }


    private void gameStarted(Bubble.ServerBubble[] serverBubbles)
    {

        Bubble[] bubbles = new Bubble[serverBubbles.length];
        for (int i = 0; i < serverBubbles.length ; i++)
        {
            bubbles[i] = new Bubble(serverBubbles[i]);
        }
        controller.gameStarted(bubbles);

    }

    private void bubblePopped(long poppedBubbleId)
    {
        controller.bubblePopped(poppedBubbleId);
    }


    private void gameOver(String winner, int winnersScore) throws IOException
    {
        System.out.println("the winner is " + winner + " and the score is " + winnersScore);
        session.close(new CloseReason(CloseReason.CloseCodes.NORMAL_CLOSURE, "Game over"));
    }


    @OnError
    public void onError(Throwable t)
    {
        t.printStackTrace();
    }

    @OnClose
    public void onClose()
    {
        //TODO handle on close logic
    }

    public void sendMessage(final Long id)
    {
        try
        {
            session.getBasicRemote().sendText(Long.toString(id));
        } catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public void connect()
    {
        WebSocketContainer container = ContainerProvider.getWebSocketContainer();
        try
        {
            container.connectToServer(this, new URI("ws://localhost:8080/recroom/bubble"));
        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
