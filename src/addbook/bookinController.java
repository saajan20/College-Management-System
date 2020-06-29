package addbook;

import com.jfoenix.controls.JFXTextField;
import dbUtil.dbConnection;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class bookinController {
    @FXML
    private JFXTextField title;
    @FXML
    private JFXTextField id;
    @FXML
    private JFXTextField author;
    @FXML
    private JFXTextField publisher;
    @FXML
    private Button saveButton;
    @FXML
    private Button cancelButton;

    private dbConnection dc;

    public void initialize(URL url, ResourceBundle resources) {
        this.dc=new dbConnection();

    }
    @FXML
    public  void addBook(ActionEvent event){
        try{this.dc=new dbConnection();
            Connection conn=dbConnection.getConnection();
            PreparedStatement stmt=conn.prepareStatement("INSERT INTO BOOK VALUES(?,?,?,?,?)");
            stmt.setString(1,this.id.getText());
            stmt.setString(2,this.title.getText());
            stmt.setString(3,this.author.getText());
            stmt.setString(4,this.publisher.getText());
            stmt.setBoolean(5,true);

           // stmt.setString(5,this.dob.getEditor().getText());
            stmt.execute();
            conn.close();
            Alert alert=new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText(null);
            alert.setContentText("Success");
            alert.showAndWait();
        }
        catch (SQLException e){
            System.err.println(e);
        }
    }
    @FXML
    public  void cancel(ActionEvent event){

    }






}
