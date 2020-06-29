package memberlist;

import bookList.BookData;
import dbUtil.dbConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class memberController implements Initializable {
    @FXML
    private TableView<memberData> table;
    @FXML
    private TableColumn<memberData, String> nameCol;
    @FXML
    private TableColumn<memberData, String> idCol;
    @FXML
    private TableColumn<memberData, String> mobCol;
    @FXML
    private TableColumn<memberData, String> emailCol;
    private String sql="SELECT * FROM Member";
    ObservableList<memberData> data;
    private dbConnection dc;
    public void initialize(URL url, ResourceBundle resources) {
        this.dc=new dbConnection();
        loadMemberData();
    }
    @FXML
    private void loadMemberData() {
        try{
            this.dc=new dbConnection();
            Connection conn=dbConnection.getConnection();
            this.data= FXCollections.observableArrayList();
            ResultSet rs=conn.createStatement().executeQuery(sql);
            while(rs.next())
            {
                this.data.add(new memberData(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4)));
            }
        }catch (SQLException E){
            System.err.println(E);
        }
        this.idCol.setCellValueFactory(new PropertyValueFactory< memberData,String>("id"));
        this.nameCol.setCellValueFactory(new PropertyValueFactory<memberData,String>("name"));
        this.mobCol.setCellValueFactory(new PropertyValueFactory<memberData,String>("mobile"));
        this.emailCol.setCellValueFactory(new PropertyValueFactory<memberData,String>("email"));
        //this.availabilityColumn.setCellValueFactory(new PropertyValueFactory<BookData,Boolean>("availability"));
        this.table.setItems(null);
        this.table.setItems(this.data);
    }



}
