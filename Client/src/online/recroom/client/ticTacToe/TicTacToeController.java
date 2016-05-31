package online.recroom.client.ticTacToe;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.SplitPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;
import online.recroom.client.Animator;
import online.recroom.client.Scener;

import javax.websocket.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by Yeedle on 5/19/2016 5:24 PM.
 */
@ClientEndpoint
public class TicTacToeController
{
    @FXML
    SplitPane pane;
    @FXML
    GridPane board;
    @FXML
    ScrollPane console;
    @FXML
    VBox vbox;

    Pane[][] squares = new Pane[3][3];

    private Session session;
    public Stage getStage(){
        return (Stage)pane.getScene().getWindow();
    }
    @OnOpen
    public void onOpen(final Session session) {this.session = session;
        System.out.println("conntected"); }

    @OnMessage
    public void onMessage(final String string){
        System.out.println("incoming msg: " + string);
        //TODO: Create a decoder to handle incoming POJOs.
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

    @OnError
    public void onError(final Session session, Throwable throwable)
    {
        System.out.println(throwable.toString());
    }


    @OnClose
    public void onClose(final Session session, final CloseReason reason)
    {
        System.out.println(reason.getReasonPhrase());
        this.session = null;
    }




    private void connectToWebSocket(final URI uri)
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


    public void initialize()
    {
        try
        {
            connectToWebSocket(new URI("ws://localhost:8080/recroom/ticTacToe/1/moshe?action=join"));
        } catch (URISyntaxException e)
        {
            e.printStackTrace();
        }

        int k = 0;
        for(int row = 0; row < 3; row++)
            for (int column = 0; column < 3; column++)
            {
                squares[column][row] = (Pane)board.getChildren().get(k);
                k++;
            }
        pane.setOnKeyPressed(e -> Animator.runningText("Hello, I'm glad you're playing me today ;)", vbox));

        console.setVvalue(1.0);
        vbox.heightProperty().addListener((observable, oldValue, newValue) -> console.setVvalue(newValue.doubleValue()));
        pane.sceneProperty().addListener((observableScene, oldScene, newScene) -> attachKeyListners(oldScene, newScene));

    }

    private void attachKeyListners(Scene oldScene, Scene newScene)
    {
        if (oldScene == null && newScene != null)
            newScene.setOnKeyPressed(e -> handleKeyStrokes(e));

    }
    private ImageView imageViewOf(String url) throws IOException
    {
        Image image = new Image(getClass().getResourceAsStream(url));
        ImageView iv = new ImageView(image);
        iv.setPreserveRatio(true);
        iv.setFitWidth(60);
        iv.setFitHeight(60);
        return iv;
    }

    private void handleKeyStrokes(KeyEvent ke)
    {
        if (ke.getCode().equals(KeyCode.BACK_SPACE))
        {
            final String PATH = "../welcome/welcome.fxml";
            FXMLLoader loader = Scener.getLoader(PATH, this.getClass());
            try
            {
                Parent root = loader.load();
                Scener.showScene(getStage(), root);
            } catch (IOException e) {e.printStackTrace();}

        }
    }

}
