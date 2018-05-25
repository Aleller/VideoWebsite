package model;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

/*提供了crud的四个方法
* 每次初始化之前，请调用initializeResources()方法
* 使用完毕后，请调用destroyResources()方法*/

public class Query {
    private String driver = "com.mysql.jdbc.Driver";
    private String url = "jdbc:mysql://localhost:3306/VideoWebsite";
    private String DBUserName = "root";
    private String DBPassword = "admin";
    private com.mysql.jdbc.Connection connection = null;
    private PreparedStatement ps = null;
    private ResultSet rs = null;

    //在使用此对象之前调用
    public void initializeResources(){
        try{
            Class.forName(driver);//classLoader
            connection = (Connection) DriverManager.getConnection(url,DBUserName,DBPassword);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e){
            e.printStackTrace();
        }

        return;
    }
    //在使用此对象之后调用
    public void destroyResources(){
        try{
            if(rs != null){
                rs.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try{
            if(ps != null){
                ps.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try{
            if(connection != null){
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ResultSet retrieve(String sql){
        try{
            ps = (PreparedStatement)connection.prepareStatement(sql);
            rs = ps.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return rs;
    }

    public int create (String sql){
        int a = 0;
        try{
            ps = (PreparedStatement)connection.prepareStatement(sql);
            a = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return a;
    }
}
