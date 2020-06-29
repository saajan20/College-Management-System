package addbook;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class bookinfo extends Application {
    public void start(Stage stage) throws Exception {
        Parent root=(Parent) FXMLLoader.load(getClass().getResource("/mainUI/main.fxml"));
        Scene scene=new Scene(root);
        stage.setScene(scene);
        stage.setTitle("School Management System");
        stage.setResizable(false);
        stage.show();
    }


}

