package online.recroom.client;

import javafx.application. Application;
import javafx.scene.Group;
import javafx.scene.Scene;
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
    public static final int HEIGHT = 650;
    public static final int WIDTH = 650;

    public static void main(String[] args) { launch();}

    @Override
    public void start(Stage primaryStage) throws Exception {

        Group root = new Group();


        Scene welcomeScene = new Scene(new WelcomeScene(), WIDTH, HEIGHT);
        primaryStage.setTitle("RecRoom");
        primaryStage.setScene(welcomeScene);
        primaryStage.show();

   /*     try {
            gameClientEndpoint = new GameClientEndpoint(new URI("ws://localhost:8080/recroom/echo"));
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        gameClientEndpoint.sendMessage("Not bad.");*/




        //TODO: JavaFX code goes here
    }
}
