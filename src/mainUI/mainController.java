package mainUI;

import addbook.bookinController;
import addmember.addmemberController;
import bookList.bool_listController;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.effects.JFXDepthManager;
import dbUtil.dbConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import memberlist.memberController;
import student.StudentsController;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.Observable;
import java.util.Optional;
import java.util.ResourceBundle;

public class mainController implements Initializable {
    @FXML
    private ImageView addmem;
    @FXML
    private JFXTextField book;
    @FXML
    private ImageView addbook;
    @FXML
    private ImageView viewbook;
    @FXML
    private ImageView viewmem;
    @FXML
    private ImageView setting;
    @FXML
    private HBox book_info;
    @FXML
    private HBox member_info;
    @FXML
    private TextField bookinput;
    @FXML
    private TextField memberinput;
    @FXML
    private Text bookname;
    @FXML
    private Text membername;
    @FXML
    private Text contact;
    @FXML
    private Text bookauthor;
    @FXML
    private Text bookstatus;
    @FXML
    private ListView<String> issuedataList;
    Boolean isReady=false;
    private dbConnection dc;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        JFXDepthManager.setDepth(book_info,2);
        JFXDepthManager.setDepth(member_info,2);
        this.dc=new dbConnection();
        Image im=new Image("icon/add.jpg");
        addmem.setImage(im);
        Image im1=new Image("icon/book.jpg");
        addbook.setImage(im1);
        Image im2=new Image("icon/viewmember.jpg");
        viewmem.setImage(im2);
        Image im3=new Image("icon/viewbook.jpg");
        viewbook.setImage(im3);
        Image im4=new Image("icon/setting.jpg");
        setting.setImage(im4);
    }

    public void member1(ActionEvent event) {

            try{
                FXMLLoader loader=new FXMLLoader();
                Parent root=loader.load(getClass().getResource("/addmember/addmember.fxml").openStream());
                Stage userStage=new Stage(StageStyle.DECORATED);

                addmemberController studentsController=(addmemberController)loader.getController();
                Scene scene = new Scene(root);
                userStage.setScene(scene);
                userStage.setTitle("New Member");
                userStage.setResizable(false);
                userStage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }

    }

    public void book1(ActionEvent event) {
        try{
            Stage userStage=new Stage();
            FXMLLoader loader=new FXMLLoader();
            Pane root=(Pane)loader.load(getClass().getResource("/addbook/bookin.fxml").openStream());
            bookinController studentsController=(bookinController)loader.getController();
            Scene scene = new Scene(root);
            userStage.setScene(scene);
            userStage.setTitle("New Book");
            userStage.setResizable(false);
            userStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void member2(ActionEvent event) {
        try{
            Stage userStage=new Stage();
            FXMLLoader loader=new FXMLLoader();
            Pane root=(Pane)loader.load(getClass().getResource("/memberlist/memberlist.fxml").openStream());
            memberController studentsController=(memberController)loader.getController();
            Scene scene = new Scene(root);
            userStage.setScene(scene);
            userStage.setTitle("Member List");
            userStage.setResizable(false);
            userStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }}

    public void book2(ActionEvent event) {
        try{
            Stage userStage=new Stage();
            FXMLLoader loader=new FXMLLoader();
            Pane root=(Pane)loader.load(getClass().getResource("/booklist/book_list.fxml").openStream());
            bool_listController studentsController=(bool_listController)loader.getController();
            Scene scene = new Scene(root);
            userStage.setScene(scene);
            userStage.setTitle("Book List");
            userStage.setResizable(false);
            userStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadbookinfo(ActionEvent event) throws SQLException {
        String id1=bookinput.getText();
        System.out.println(id1);
        String q="SELECT * FROM BOOK WHERE id= '"+id1+"' ";
        Connection conn= dbConnection.getConnection();
        ResultSet rs=conn.createStatement().executeQuery(q);
       // System.out.println(rs);
        Boolean flag=false;
        while(rs.next())
        {
            String bName=rs.getString("title");
            String bAuthor=rs.getString("author");
            Boolean bstatus=rs.getBoolean("isAvail");
            bookname.setText(bName);
            bookauthor.setText(bAuthor);
            if(bstatus)bookstatus.setText("Available");else bookstatus.setText("Unavailable");
            flag=true;
        }
        if(!flag)
        {
            bookname.setText("No Such Book Available"); bookauthor.setText("");bookstatus.setText("");
        }
    }

    public void loadmemberinfo(ActionEvent event) throws SQLException{
        String id1=memberinput.getText();
  //      System.out.println(id1);
        String q="SELECT * FROM Member WHERE ID= '"+id1+"' ";
        Connection conn= dbConnection.getConnection();
        ResultSet rs=conn.createStatement().executeQuery(q);
        // System.out.println(rs);
        Boolean flag=false;
        while(rs.next())
        {
            String bName=rs.getString("Name");
           String bAuthor=rs.getString("Mobile");
          //  Boolean bstatus=rs.getBoolean("isAvail");
            membername.setText(bName);
          contact.setText(bAuthor);
            //if(bstatus)bookstatus.setText("Available");else bookstatus.setText("Unavailable");
            flag=true;
        }
        if(!flag)
        {
            membername.setText("No Such Member");
            contact.setText("");
        }
    }

    public void loadissue(ActionEvent event) {
        String memberID=memberinput.getText();
        String bookID=bookinput.getText();
        Alert alert=new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirm issue operation");
        alert.setHeaderText(null);
        alert.setContentText("Are you sure to issue the book "+ bookname.getText() +"\n to "+ membername.getText() + " ?");
        Optional<ButtonType> response =alert.showAndWait();
        if (response.get()==ButtonType.OK){
            try{this.dc=new dbConnection();
                Connection conn=dbConnection.getConnection();
                PreparedStatement stmt=conn.prepareStatement("INSERT INTO ISSUE(bookID,memberID) VALUES(?,?)");
                 stmt.setString(1,bookID);
                stmt.setString(2,memberID);
                stmt.execute();
                PreparedStatement stmt2=conn.prepareStatement("UPDATE BOOK SET isAvail= false WHERE id=   '"+bookID+"'     ");
                stmt2.execute();
                conn.close();
                Alert alert1=new Alert(Alert.AlertType.INFORMATION);
                alert1.setHeaderText(null);
                alert1.setContentText("Success");
                alert1.showAndWait();
            }
            catch (SQLException e){
                System.err.println(e);
            }
        }
else{

            Alert alert1=new Alert(Alert.AlertType.INFORMATION);
            alert1.setHeaderText(null);
            alert1.setContentText("book issue cancelled");
            alert1.showAndWait();

        }



    }

    public void loadinfo(ActionEvent event) throws SQLException {
        ObservableList<String> issueData= FXCollections.observableArrayList();
        String id=book.getText();
        String q="SELECT * FROM ISSUE WHERE bookID= '"+id+"' ";
        Connection conn= dbConnection.getConnection();
        ResultSet rs=conn.createStatement().executeQuery(q);
        while (rs.next()){
            String mBookID=id;
            String mMemberID=rs.getString("memberID");
         // Time t=rs.getTime("issueTime");
            int renewcount=rs.getInt("renew_count");
          // issueData.add("Issue Date and Time :"+t.toGMTString());
            issueData.add("Renew count: " +renewcount);
            issueData.add("Book Information:- ");
            q="SELECT * FROM BOOK WHERE id= '"+mBookID+"' ";
            ResultSet rs1=conn.createStatement().executeQuery(q);
            while (rs1.next()){
                issueData.add("         Book Name: "+rs1.getString("title"));
                issueData.add("         Book ID: "+rs1.getString("id"));
                issueData.add("         Book Author: "+rs1.getString("author"));
                issueData.add("         Book Publisher: "+rs1.getString("publisher"));
            }
            q="SELECT * FROM Member WHERE ID= '"+mMemberID+"' ";
           rs1=conn.createStatement().executeQuery(q);
            issueData.add("Member Information:-");
            while (rs1.next()){
                issueData.add("         Name: "+rs1.getString("Name"));
                issueData.add("         Mobile: "+rs1.getString("Mobile"));
                issueData.add("         Email: "+rs1.getString("email"));

            }


isReady=true;

        }

 issuedataList.getItems().setAll(issueData);


    }

    public void loadsubmission(ActionEvent event) throws SQLException {
        String id=book.getText();
        if(!isReady)
        {
            Alert alert=new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Failed");
            alert.setHeaderText(null);
            alert.setContentText("Please enter valid Book ID");
            alert.showAndWait();


            return;}
        Alert alert1=new Alert(Alert.AlertType.CONFIRMATION);
        alert1.setTitle("Confirm Submission Operation");
        alert1.setHeaderText(null);
        alert1.setContentText("Are you sure...?");
        Optional<ButtonType> response =alert1.showAndWait();
        if (response.get()==ButtonType.OK) {
            Connection conn=dbConnection.getConnection();
            PreparedStatement stmt=conn.prepareStatement("DELETE FROM ISSUE WHERE bookID=  '"+id+"'  ");
            stmt.execute();
            PreparedStatement stmt2=conn.prepareStatement("UPDATE BOOK SET isAvail=true WHERE id=   '"+id+"'     ");
            stmt2.execute();
            conn.close();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Success");
            alert.setHeaderText(null);
            alert.setContentText("Book has been Submitted");
            alert.showAndWait();

        }




    }

    public void renew(ActionEvent event) throws SQLException {
        String id=book.getText();
        if(!isReady)
        {
            Alert alert=new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Failed");
            alert.setHeaderText(null);
            alert.setContentText("Please enter valid Book ID");
            alert.showAndWait();


            return;}
        Alert alert1=new Alert(Alert.AlertType.CONFIRMATION);
        alert1.setTitle("Confirm Renew Operation");
        alert1.setHeaderText(null);
        alert1.setContentText("Are you sure about renew...?");
        Optional<ButtonType> response =alert1.showAndWait();
        if (response.get()==ButtonType.OK) {
            Connection conn=dbConnection.getConnection();
            PreparedStatement stmt=conn.prepareStatement("UPDATE ISSUE SET issueTime = CURRENT_TIMESTAMP,renew_count=renew_count+1 WHERE bookID= '"+id+"'  ");
            stmt.execute();

            conn.close();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Success");
            alert.setHeaderText(null);
            alert.setContentText("Book has been Renewed");
            alert.showAndWait();

        }





    }
}
