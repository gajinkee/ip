package gui;

import java.util.Objects;

import javafx.animation.PauseTransition;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.util.Duration;
import skynet.Skynet;

/**
 * Controller for the main GUI.
 */
public class MainWindow extends AnchorPane {
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;

    private Skynet skynet;

    private final Image userImage = new Image(Objects.requireNonNull(this.getClass()
            .getResourceAsStream("/images/DaUser.png")));
    private final Image skynetImage = new Image(Objects.requireNonNull(this.getClass()
            .getResourceAsStream("/images/Skynet.png")));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /** Injects the Skynet instance */
    public void setSkynet(Skynet d) {
        skynet = d;
    }

    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = skynet.getResponse(input);

        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getSkynetDialog(response, skynetImage)
        );
        userInput.clear();

        if (response.equals("EXIT")) {
            PauseTransition delay = new PauseTransition(Duration.seconds(0.5)); // 2-second delay
            delay.setOnFinished(event -> Platform.exit()); // Exit after delay
            delay.play();
        }

    }
}
