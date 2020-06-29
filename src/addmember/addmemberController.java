package addmember;

import com.jfoenix.controls.JFXTextField;
import dbUtil.dbConnection;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class addmemberController implements Initializable {
    @FXML
    private JFXTextField name;
    @FXML
    private JFXTextField id;
    @FXML
    private JFXTextField mob;
    @FXML
    private JFXTextField email;
    @FXML
    private Button saveButton;
    @FXML
    private Button cancelButton;

    private dbConnection dc;
@Override
    public void initialize(URL url, ResourceBundle resources) {
        this.dc=new dbConnection();

    }


    public void cancel(ActionEvent actionEvent) {
        try{
            Stage userStage=new Stage();
            FXMLLoader loader=new FXMLLoader();
            Pane root=(Pane)loader.load(getClass().getResource("/memberlist/memberlist.fxml").openStream());
            Scene scene = new Scene(root);
            userStage.setScene(scene);
            userStage.setTitle("Admin");
            userStage.setResizable(false);

            userStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void addMember(ActionEvent actionEvent) {
        try{this.dc=new dbConnection();
            Connection conn=dbConnection.getConnection();
            PreparedStatement stmt=conn.prepareStatement("INSERT INTO Member VALUES(?,?,?,?)");
            stmt.setString(1,this.id.getText());
            stmt.setString(2,this.name.getText());
            stmt.setString(3,this.mob.getText());
            stmt.setString(4,this.email.getText());
            //stmt.setBoolean(5,true);

            // stmt.setString(5,this.dob.getEditor().getText());
            stmt.execute();
            conn.close();
            Alert alert=new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText(null);
            alert.setContentText("Saved");
            alert.showAndWait();
        }
        catch (SQLException e){
            System.err.println(e);
        }



    }
}
