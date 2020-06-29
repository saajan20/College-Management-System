package Login;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import dbUtil.dbConnection;
public class Loginmodel {
    Connection connection;
    public Loginmodel(){
        try{
            this.connection=dbConnection.getConnection();
        }
        catch(SQLException e){
            e.printStackTrace();
        }
        if(this.connection==null)
        {
            System.exit(1);
        }
    }
    public boolean isDatabaseConnected(){
        return this.connection!=null;
    }
    public boolean isLogin(String user,String pass,String opt) throws Exception {
        PreparedStatement p=null;
        ResultSet r=null;
        String sql = "SELECT * FROM login where username = ? and password =? and division=?";
        try {
            p = this.connection.prepareStatement(sql);
            p.setString(1, user);
            p.setString(2, pass);
            p.setString(3, opt);
            r = p.executeQuery();
            if (r.next()) {
                return true;
            }
            return false;

        } catch (SQLException e) {
            return false;
        } finally
            {
                p.close();
                r.close();
            }
        }





}
