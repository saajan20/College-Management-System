package bookList;

import Admin.StudentData;
import dbUtil.dbConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
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

public class bool_listController implements Initializable {
    @FXML
    private TableView<BookData> tableview;
    @FXML
    private TableColumn<BookData, String> titleColumn;
    @FXML
    private TableColumn<BookData, String> idColumn;
    @FXML
    private TableColumn<BookData, String> authorColumn;
    @FXML
    private TableColumn<BookData, String> publisherColumn;
    @FXML
    private TableColumn<BookData, Boolean> availabilityColumn;
    @FXML
    private Button dd;
    private String sql="SELECT * FROM BOOK";
     ObservableList<BookData> data;
    private dbConnection dc;
    public void initialize(URL url, ResourceBundle resources) {
        this.dc=new dbConnection();
        loadbookData();
    }
@FXML
    private void loadbookData() {
        try{
            this.dc=new dbConnection();
            Connection conn=dbConnection.getConnection();
            this.data= FXCollections.observableArrayList();
            ResultSet rs=conn.createStatement().executeQuery(sql);
            while(rs.next())
            {
                this.data.add(new BookData(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getBoolean(5)));
            }
        }catch (SQLException E){
            System.err.println(E);
        }
        this.idColumn.setCellValueFactory(new PropertyValueFactory< BookData,String>("id"));
        this.titleColumn.setCellValueFactory(new PropertyValueFactory<BookData,String>("title"));
        this.authorColumn.setCellValueFactory(new PropertyValueFactory<BookData,String>("author"));
        this.publisherColumn.setCellValueFactory(new PropertyValueFactory<BookData,String>("publisher"));
        this.availabilityColumn.setCellValueFactory(new PropertyValueFactory<BookData,Boolean>("availability"));
        this.tableview.setItems(null);
        this.tableview.setItems(this.data);
    }




    }

