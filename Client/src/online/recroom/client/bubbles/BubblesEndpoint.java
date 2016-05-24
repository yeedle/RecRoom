package online.recroom.client.bubbles;

import javafx.animation.*;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;

import javax.websocket.*;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by Yeedle on 5/17/2016 9:32 AM.
 */
@ClientEndpoint (decoders = MessageDecoder.class)
public class BubblesEndpoint implements Initializable
{

    private Session session;
    @FXML
    Group bubblePane;

    ConcurrentHashMap<Long, Bubble> bubbleMap = new ConcurrentHashMap<>();
    File popSoundFile =  new File("Client/src/online/recroom/client/assets/pop.mp3");
    Media popSound = new Media(popSoundFile.toURI().toString());
    Timeline t = new Timeline();
    private double bubbleSpeed = 40;

    @OnOpen
    public void onOpen(final Session session, Message message)
    {
        t.setCycleCount(Timeline.INDEFINITE);
        t.play();
    }

    @OnMessage
    public void onMessage(final Message message) throws IOException
    {
        switch (message.type)
        {
            case GAME_STARTED:
                gameStarted(message.newBubble);
                break;
            case BUBBLE_POPPED:
                bubblePopped(message.poppedBubbleId);
                break;
            case GAME_OVER:
                gameOver(message.winner, message.winnersScore);
                break;
            default:
                //TODO error if can't decode
                break;
        }
    }

    private void gameStarted(Bubble.ServerBubble[] bubbles)
    {
        for (Bubble.ServerBubble serverBubble : bubbles)
        {
            Bubble bubble = new Bubble(serverBubble);
            bubble.setOnMouseClicked(e -> onClickRemove(bubble.id));
            t.getKeyFrames().add(new KeyFrame(Duration.millis(bubbleSpeed), e -> bubble.move()));
            bubbleMap.put(bubble.id, bubble);
            bubblePane.getChildren().add(bubble);
        }

        t.play();
    }

    private void bubblePopped(long poppedBubbleId)
    {
        Bubble bubble = bubbleMap.get(poppedBubbleId);
        ScaleTransition st = new ScaleTransition(Duration.millis(100), bubble);
        st.setByX(5);
        st.setByY(5);
        st.setOnFinished(e -> {bubblePane.getChildren().remove(bubble); new MediaPlayer(popSound).play();});
        st.play();
    }


    private void gameOver(String winner, int winnersScore) throws IOException
    {
        System.out.println("the winner is " + winner + " and the score is " + winnersScore);
        session.close(new CloseReason(CloseReason.CloseCodes.NORMAL_CLOSURE, "Game over"));
    }


    private void onClickRemove(long bubbleId)
    {
        sendMessage(bubbleId);
        bubblePopped(bubbleId);
    }

    @OnError
    public void onError()
    {

    }

    @OnClose
    public void onClose()
    {
        //TODO handle on close logic
    }

    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        //TODO connect to websockets

        /*Bubble bubble = new Bubble(1, .5, .4,.1, .1, .02);

        bubbleMap.put(bubble.id, bubble);
        bubblePane.getChildren().add(bubble);
        bubble.setOnMouseClicked(e -> onClickRemove(bubble.id));
       t.getKeyFrames().add(new KeyFrame(Duration.millis(70), e -> bubble.move()));
        t.setCycleCount(Animation.INDEFINITE);
       t.play();
*/
     /*   try
        {
            connectToBubbleServer(new URI("ws://")); //TODO: add ws URI of Server Endpoint
        } catch (URISyntaxException e)
        {
            e.printStackTrace();
        }*/
    }

    private void connectToBubbleServer(URI uri)
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



}
