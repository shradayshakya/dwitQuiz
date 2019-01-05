package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DatabaseConnection {

    final String url = "jdbc:mysql://localhost:3306/dwitphase2";
    final String user = "root";
    final String password = "";
    Connection connection = null;
    public DatabaseConnection(){
        try {
            this.connection= DriverManager.getConnection(url,user,password);
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
    }

    public final PreparedStatement getPreparedStatement(String sql){
        try {
            return this.connection.prepareStatement(sql);
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
            return null;
        }
    }


    public void closeConnection(){
        try {
            connection.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
