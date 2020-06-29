package application;

import bookList.BookData;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.*;
import java.lang.*;

public class acontroller implements Initializable {
    @FXML
    private TableView<adata> tv;
    @FXML
    private TableColumn<adata, String> fname;
    @FXML
    private TableColumn<adata, String> lname;
    @FXML
    private TableColumn<adata, String> roll;
    @FXML
    private TableColumn<adata, String> date;
    @FXML
    private TableColumn<adata, String> time;
    FaceDetector faceDetect= new FaceDetector();
   private Database d;
    ArrayList<String> user = new ArrayList<String>();
   String fn=SampleController.F;

    ObservableList<adata> data;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
this.d= new Database();
System.out.println(fn);
loadattendance();
    }

    private void loadattendance() {
        try{
            user=faceDetect.getOutput();
            boolean conn=d.init();
             String sql="SELECT * FROM attendance where FNAME='"+fn+"'";
            this.data= FXCollections.observableArrayList();
            ResultSet rs=d.con.createStatement().executeQuery(sql);
            while(rs.next())
            {
                this.data.add(new adata(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5)));
            }
        }catch (SQLException E){
            System.err.println(E);
        }
        this.fname.setCellValueFactory(new PropertyValueFactory< adata,String>("fname"));
        this.lname.setCellValueFactory(new PropertyValueFactory<adata,String>("lname"));
        this.roll.setCellValueFactory(new PropertyValueFactory<adata,String>("roll"));
        this.date.setCellValueFactory(new PropertyValueFactory<adata,String>("date"));
        this.time.setCellValueFactory(new PropertyValueFactory<adata,String>("time"));
        this.tv.setItems(null);
        this.tv.setItems(this.data);
    }
}
