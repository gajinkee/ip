package gui;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import skynet.Skynet;

/**
 * A GUI for Skynet using FXML.
 */
public class Main extends Application {

    private final Skynet skynet = new Skynet();

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setSkynet(skynet);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
