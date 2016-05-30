package online.recroom.client;

import javafx.application. Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;


/**
 * This class is the entry point for our JavaFX client
 */

public class RecRoom extends Application {

    public static final int HEIGHT = 640;
    public static final int WIDTH = 640;

    public static void main(String[] args) { launch();}

    @Override
    public void start(Stage primaryStage) throws Exception {

      //  Group root = new Group();

        Parent root = FXMLLoader.load(getClass().getResource("welcome/welcome.fxml"));
        Scene welcomeScene = new Scene(root, WIDTH, HEIGHT);
        primaryStage.setTitle("RecRoom");
        primaryStage.setScene(welcomeScene);
        primaryStage.show();
       primaryStage.getIcons().addAll(new Image(getClass().getResourceAsStream("assets/icon.png")));

     /* try {
            gameClientEndpoint = new GameClientEndpoint(new URI("ws://localhost:8080/recroom/echo"));
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        gameClientEndpoint.sendMessage("Not bad.");*/




        //TODO: JavaFX code goes here
    }
}
