package online.recroom.client;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Created by Yeedle on 5/28/2016 10:52 PM.
 */
public class Scener
{
    public static <T> FXMLLoader getLoader(String PATH, Class<T> tClass)
    {
        return new FXMLLoader(tClass.getResource(PATH));
    }

    public static void showScene(ActionEvent event, Parent root)
    {
        Stage stage=(Stage) ((Node)event.getSource()).getScene().getWindow();
        showScene(stage, root);
    }

    public static void showScene(Stage stage, Parent root)
    {
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
