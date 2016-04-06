package online.recroom.client;

import javafx.application. Application;
import javafx.stage.Stage;

import javax.websocket.*;
import java.io.IOException;
import java.net.URI;
import java.util.logging.Logger;

/**
 * This class is both the entry point for our JavaFX client
 * as well as the webSocket's Client Endpoint.
 */

@ClientEndpoint
public class RecRoom extends Application
{
    private Session session;
    private static final Logger LOGGER = Logger.getLogger(RecRoom.class.getName());

    public static void main(String[] args)
    {
        launch();
    }

    @Override
    public void start(Stage primaryStage) throws Exception
    {
        connectToWebSoket();
        //TODO: JavaFX code goes here
    }

    @OnOpen
    public void onOpen(Session session)
    {
        this.session = session;
    }

    @OnClose
    public void onClose(){
        connectToWebSoket();
    }

    @OnMessage
    public void onMessage(String message){
        //TODO: Create a decoder to handle incoming POJOs.
    }

    private void connectToWebSoket()
    {

        WebSocketContainer containter = ContainerProvider.getWebSocketContainer();
        try {
            URI uri = URI.create("ws://localhost:8181/games");
            containter.connectToServer(this, uri);
        } catch (DeploymentException | IOException e) {
            e.printStackTrace();
        }

    }
}
