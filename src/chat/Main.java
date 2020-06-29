package chat;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class Main extends Application {

    private boolean isServer =false;

    @FXML
    private TextArea messages ;
    @FXML
    private TextField input;
    private Controller connection = isServer ? createServer() : createClient();



    @Override
    public void init() throws Exception {
        connection.startConnection();
        System.out.println("I'm init");
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

    }

    @Override
    public void stop() throws Exception {
        connection.closeConnection();
        System.out.println("I'm stop");
    }

    private Server createServer() {
        return new Server(55555, data -> {
            Platform.runLater(() -> {
                messages.appendText(data.toString() + "\n");
                System.out.println("I'm create server");
            });
        });
    }

    private Client createClient() {
        return new Client("127.0.0.1", 55555, data -> {
            Platform.runLater(() -> {
                messages.appendText(data.toString() + "\n");
                System.out.println("I'm createclient");
            });
        });
    }

    public static void main(String[] args) {
        System.out.println("I'm main");
    }

    public void in() {
        input.setOnAction((ActionEvent event) -> {
            String message = isServer ? "Teacher: " : "Saajan: ";
            message += input.getText();
            input.clear();
            System.out.println("I'm input");
            messages.appendText(message + "\n");

            try {
                connection.send(message);
            }
            catch (Exception e) {
                messages.appendText("Student is Offline\n");
            }
        });
    }
}