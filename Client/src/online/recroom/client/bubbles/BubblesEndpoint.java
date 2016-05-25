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
    private String  WebSocketURI;

    public BubblesEndpoint(String username, BubblesController controller)
    {

        this.username = username.isEmpty()? "Anonymous" : username;
        WebSocketURI = "ws://localhost:8080/recroom/bubble?username=" + username;
        this.controller = controller;
    }

    @OnOpen
    public void onOpen(final Session session)
    {
        this.session = session;
        controller.setEndpoint(this);
        controller.console("Hello, " + username + "!");
    }

    @OnMessage
    public void onMessage(final Message message) throws IOException
    {
        System.out.println("message received");
        switch (message.type)
        {
            case GAME_PENDING:
                controller.console("Waiting for another player to join...");
                break;
            case JOINED_GAME:
                joinedGame(message);
                break;
            case GAME_STARTED:
                {
                    controller.console("GO!");
                    gameStarted(message.newBubbles);
                    break;
                }
            case PLAYER_JOINED:
                controller.console(message.playerName + " joined your game");
            case BUBBLE_POPPED:
                bubblePopped(message.poppedBubbleId);
                break;
            case GAME_OVER:
                gameOver(message.winner, message.winnersScore);
                break;
            case PLAYER_LEFT:
                controller.console(message.playerName + " couldn't take the heat");
            default:

                break;
        }
    }

    private void joinedGame(Message message)
    {
        BubblePlayer[] players = message.players;
        String str = "";
        for (int i = 0; i < players.length-1; i++)
        {
            str += players[i].name + ", ";
        }
        str += "and " + players[players.length-1].name +".";
        controller.console("You joined a game with " + str);
        gameStarted(message.newBubbles);
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
        controller.console("the winner is " + winner + " and the score is " + winnersScore);
        session.close(new CloseReason(CloseReason.CloseCodes.NORMAL_CLOSURE, "Game over"));
    }


    @OnError
    public void onError(Throwable t)
    {

        controller.console("Something went horribly wrong. :(");
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
            container.connectToServer(this, new URI(WebSocketURI));
        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
