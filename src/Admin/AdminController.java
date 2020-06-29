package Admin;

import chat.Main;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import dbUtil.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import student.StudentsController;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class AdminController implements Initializable {
    @FXML
    private TextField id;
    @FXML
    private TextField firstname;
    @FXML
    private TextField lastname;
    @FXML
    private TextField email;
    @FXML
    private DatePicker dob;
    @FXML
    private TableView<StudentData> studenttable;
    @FXML
    private TableColumn<StudentData, String> idcolumn;
    @FXML
    private TableColumn<StudentData, String> firstnamecolumn;
    @FXML
    private TableColumn<StudentData, String> lastnamecolumn;
    @FXML
    private TableColumn<StudentData, String> emailcolumn;
    @FXML
    private TableColumn<StudentData, String> dobcolumn;
    @FXML
    private ImageView p;

    private String sql="SELECT * FROM students";
    int c;

private dbConnection dc;
private ObservableList<StudentData> data;
    @Override
    public void initialize(URL url, ResourceBundle resources) {
        this.dc=new dbConnection();
         c=0;


    }
    @FXML
    private void loadStudentData(ActionEvent event)throws SQLException {
        try{
            Connection conn=dbConnection.getConnection();
            this.data= FXCollections.observableArrayList();
            ResultSet rs=conn.createStatement().executeQuery(sql);
            while(rs.next())
            {
                this.data.add(new StudentData(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5)));
            }
        }catch (SQLException E){
System.err.println(E);
        }
        this.idcolumn.setCellValueFactory(new PropertyValueFactory<StudentData,String>("ID"));
        this.firstnamecolumn.setCellValueFactory(new PropertyValueFactory<StudentData,String>("firstName"));
        this.lastnamecolumn.setCellValueFactory(new PropertyValueFactory<StudentData,String>("lastName"));
        this.emailcolumn.setCellValueFactory(new PropertyValueFactory<StudentData,String>("email"));
        this.dobcolumn.setCellValueFactory(new PropertyValueFactory<StudentData,String>("DOB"));
        this.studenttable.setItems(null);
        this.studenttable.setItems(this.data);
    }
    @FXML
    private void addStudent(ActionEvent event){
        try{
Connection conn=dbConnection.getConnection();
            PreparedStatement stmt=conn.prepareStatement("INSERT INTO students VALUES(?,?,?,?,?)");
            stmt.setString(1,this.id.getText());
            stmt.setString(2,this.firstname.getText());
            stmt.setString(3,this.lastname.getText());
            stmt.setString(4,this.email.getText());
            stmt.setString(5,this.dob.getEditor().getText());
            stmt.execute();
            conn.close();
        }
        catch (SQLException e){
            System.err.println(e);
        }
    }
    @FXML
    private void deleteData(ActionEvent event){
        try {
            StudentData s = studenttable.getSelectionModel().getSelectedItem();
            String DID = s.getID();
            Connection conn = dbConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement("DELETE FROM students WHERE ID=?");
            stmt.setString(1, DID);
            stmt.execute();
            conn.close();
            studenttable.getItems().removeAll(studenttable.getSelectionModel().getSelectedItem());
            c=0;
        }
catch(SQLException e)
        {
            System.err.println(e);
        }
    }
    @FXML
    private void chat(ActionEvent event){
        try{
            Stage userStage=new Stage();
            FXMLLoader loader=new FXMLLoader();
            Pane root=(Pane)loader.load(getClass().getResource("/chat/sample.fxml").openStream());
            Scene scene = new Scene(root);
            userStage.setScene(scene);
            userStage.setTitle("Let's Chat");
            userStage.setResizable(false);

            userStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
        }
    @FXML
    private void faceAction(ActionEvent event){
        try{
            Stage primaryStage=new Stage();
            BorderPane root = (BorderPane)FXMLLoader.load(getClass().getResource("/application/Sample.fxml"));
            Scene scene = new Scene(root,1350,720);

            scene.getStylesheets().add(getClass().getResource("/application/application.css").toExternalForm());
            primaryStage.getIcons().add(new Image("logo.png"));
            primaryStage.setTitle("Shree L.R Tiwari College Of Engineering | Attendance System");

            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @FXML
    private void clearfield(ActionEvent event){
        this.id.setText("");
        this.firstname.setText("");
        this.lastname.setText("");
        this.email.setText("");
        this.dob.setValue(null);
    }

    public void saajan(MouseEvent mouseEvent) throws IOException {


        StudentData s = studenttable.getSelectionModel().getSelectedItem();
        String DID = s.getFirstName();
        if(DID.contentEquals("saajan"))
            c++;
        else
            c=0;
        if(c==2) {
            Stage stage = (Stage) this.studenttable.getScene().getWindow();
            stage.close();
            Stage userStage = new Stage();
            FXMLLoader loader = new FXMLLoader();
            Pane root = (Pane) loader.load(getClass().getResource("/Admin/saajan.fxml").openStream());
            AdminController studentsController = (AdminController) loader.getController();
            Scene scene = new Scene(root);
            userStage.setScene(scene);
            userStage.setTitle("Library");
            userStage.setResizable(false);
            userStage.show();
            c=0;

        }
    }

    public void prev(MouseEvent mouseEvent) {
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
    }

}
