package online.recroom.client;

import javafx.animation.KeyFrame;
import javafx.animation.ScaleTransition;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.Pane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;

import javax.websocket.*;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by Yeedle on 5/17/2016 9:32 AM.
 */
@ClientEndpoint
public class BubblesEndpoint implements Initializable
{
    private Session session;
    @FXML
    Pane bubblePane;

    File popSoundFile =  new File("src/online/recroom/client/assets/pop.mp3");
    Media popSound = new Media(popSoundFile.toURI().toString());
    Timeline t = new Timeline();

    @OnOpen
    public void onOpen(final Session session)
    {

        t.setCycleCount(Timeline.INDEFINITE);
        t.play();
    }

    @OnMessage
    public void onMessage(final Bubble bubble)
    {

        bubble.setOnMouseClicked(e -> mouseClickHandler(bubble));

        t.getKeyFrames().add(new KeyFrame(Duration.millis(40), e -> bubble.move()));
        bubblePane.getChildren().add(bubble);

    }

    private void mouseClickHandler(Bubble bubble)
    {
        sendMessage(Long.toString(bubble.id));
        ScaleTransition st = new ScaleTransition(Duration.millis(100), bubble);
            st.setByX(5);
            st.setByY(5);
            st.setOnFinished(e -> {bubblePane.getChildren().remove(bubble); new MediaPlayer(popSound).play();});
            st.play();
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



}
