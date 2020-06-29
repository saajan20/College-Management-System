package Login;

import Admin.AdminController;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import mainUI.mainController;
import student.StudentsController;


import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class LoginController implements Initializable {

    Loginmodel logi = new Loginmodel();
    @FXML
    private Label dbstatus;
    @FXML
    private Label LoginStatus;
    @FXML
    private ImageView i;
    @FXML
    private TextField username;
    @FXML
    private PasswordField password;
    @FXML
    private ComboBox<option> combobox;
    @FXML
    private Button loginButton;

    public void initialize(URL url, ResourceBundle rb) {
        if (this.logi.isDatabaseConnected())
            this.dbstatus.setText("Connected");
        else
            this.dbstatus.setText("Not Connected");

        this.combobox.setItems(FXCollections.observableArrayList(option.values()));
        Image im=new Image("image/download.jpg");
        i.setImage(im);

    }

    @FXML

    public void Login(ActionEvent event) {
       // this.LoginStatus.setText("Wrong Credential");
        try {
            if (this.logi.isLogin(this.username.getText(), this.password.getText(), ((option) this.combobox.getValue()).toString())) {
               // this.LoginStatus.setText("Wrong Credential 1");
                Stage stage = (Stage) this.loginButton.getScene().getWindow();
                stage.close();
                switch (((option) this.combobox.getValue()).toString()) {
                    case "Admin":
                        adminLogin();
                        break;
                    case "Library":
                        studentLogin();
                        break;
                }
               // this.LoginStatus.setText("Wrong Credential 2");
            } else {
                this.LoginStatus.setText("Wrong Credential");
            }
        } catch (Exception Local) {

        }


    }

    public void studentLogin(){
try{
    Stage userStage=new Stage();
    FXMLLoader loader=new FXMLLoader();
    Pane root=(Pane)loader.load(getClass().getResource("/mainUI/main.fxml").openStream());
    mainController studentsController=(mainController)loader.getController();
    Scene scene = new Scene(root);
    userStage.setScene(scene);
    userStage.setTitle("Library");
    userStage.setResizable(false);
    userStage.show();
} catch (IOException e) {
    e.printStackTrace();
}
    }
    public void adminLogin(){
        try{
            Stage adminStage=new Stage();
            FXMLLoader adminLoader=new FXMLLoader();
            Pane adminroot=(Pane)adminLoader.load(getClass().getResource("/Admin/Admin.fxml").openStream());
            AdminController adminController=(AdminController)adminLoader.getController();
            Scene scene = new Scene(adminroot);
            adminStage.setScene(scene);
            adminStage.setTitle("Admin Dashboard");
            adminStage.setResizable(false);
            adminStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }



    }}

