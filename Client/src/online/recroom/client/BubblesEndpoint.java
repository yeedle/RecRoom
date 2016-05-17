package online.recroom.client;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.Pane;

import javax.annotation.Resources;
import javax.websocket.*;
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

    @OnOpen
    public void onOpen(final Session session)
    {

    }

    @OnMessage
    public void onMessage(final Bubble bubble)
    {
        this.bubblePane.getChildren().add(bubble);
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


}
