package online.recroom.client.bubbles;

import javafx.animation.KeyFrame;
import javafx.animation.ScaleTransition;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.Pane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;

import javax.sound.midi.Soundbank;
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
@ClientEndpoint
        (decoders = MessageDecoder.class)

public class BubblesEndpoint implements Initializable
{
    private Session session;
    @FXML
    Pane bubblePane;
    ConcurrentHashMap<Long, Bubble> bubbleMap = new ConcurrentHashMap<>();
    File popSoundFile =  new File("src/online/recroom/client/online.recroom.client.assets/pop.mp3");
    Media popSound = new Media(popSoundFile.toURI().toString());
    Timeline t = new Timeline();

    @OnOpen
    public void onOpen(final Session session)
    {
        t.setCycleCount(Timeline.INDEFINITE);
        t.play();
    }

    @OnMessage
    public void onMessage(final Message message)
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

    private void gameStarted(Bubble[] bubbles)
    {
        for (Bubble bubble : bubbles)
        {
            bubble.setOnMouseClicked(e -> onClickRemove(bubble.id));
            t.getKeyFrames().add(new KeyFrame(Duration.millis(40), e -> bubble.move()));
            bubbleMap.put(bubble.id, bubble);
        }

        bubblePane.getChildren().addAll(bubbles);
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
        session.close(new CloseReason(CloseReason.Cl));
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

    }

    @Override
    public void initialize(URL location, ResourceBundle resources)
    {

        try
        {
            connectToBubbleServer(new URI("ws://")); //TODO: add ws URI of Server Endpoint
        } catch (URISyntaxException e)
        {
            e.printStackTrace();
        }
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
