package online.recroom.client;

import javafx.application. Application;
import javafx.scene.Group;
import javafx.stage.Stage;

import javax.websocket.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.logging.Logger;

/**
 * This class is the entry point for our JavaFX client
 */

public class RecRoom extends Application {

    GameClientEndpoint gameClientEndpoint;

    public static void main(String[] args) {


        launch();
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        try {
            gameClientEndpoint = new GameClientEndpoint(new URI("ws://localhost:8080/recroom/echo"));
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        gameClientEndpoint.sendMessage("Not bad.");


        Group root = new Group();

        //TODO: JavaFX code goes here
    }
}
