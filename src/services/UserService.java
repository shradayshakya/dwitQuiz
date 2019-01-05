package services;

import domains.User;
import utils.DatabaseConnection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserService{

    public String insertUser(String name, String email, String password){
        String message = "";

        DatabaseConnection db = new DatabaseConnection();
        String sql = "INSERT INTO users(name, email, password, role) VALUES (?,?,?,'user');";
        try{
            PreparedStatement ps = db.getPreparedStatement(sql);
            ps.setString(1, name);
            ps.setString(2, email);
            ps.setString(3, password);
            ps.execute();
            message = "User successfully registered";
        }catch (SQLException e){
            message = e.getMessage();
        }
        return  message;
    }

    public User getUser(String email, String password){
        DatabaseConnection db = new DatabaseConnection();
        String sql = "SELECT * FROM users WHERE email = ? AND password = ?;";
        User user = null;
        try{
            PreparedStatement ps = db.getPreparedStatement(sql);
            ps.setString(1, email);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                user = new User();
                user.setId(rs.getInt("id"));
                user.setName(rs.getString("name"));
                user.setEmail(rs.getString("email"));
                user.setPassword(rs.getString("password"));
                user.setRole(rs.getString("role"));
            }
            rs.close();
        }catch (SQLException e){
            e.getMessage();
        }
        return user;
    }

    public User getUser(int id){
        DatabaseConnection db = new DatabaseConnection();
        String sql = "SELECT * FROM users WHERE id = ?";
        User user = null;
        try{
            PreparedStatement ps = db.getPreparedStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                user = new User();
                user.setId(rs.getInt("id"));
                user.setName(rs.getString("name"));
                user.setEmail(rs.getString("email"));
                user.setPassword(rs.getString("password"));
                user.setRole(rs.getString("role"));
            }
            rs.close();
        }catch (SQLException e){
            e.getMessage();
        }
        return user;
    }

    public List<User> getAllUsers(){
        DatabaseConnection db = new DatabaseConnection();
        String sql = "SELECT * FROM users";
        List<User> users = new ArrayList<User>();
        try{
            PreparedStatement ps = db.getPreparedStatement(sql);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                User user = new User();
                user.setId(rs.getInt("id"));
                user.setName(rs.getString("name"));
                user.setEmail(rs.getString("email"));
                user.setPassword(rs.getString("password"));
                user.setRole(rs.getString("role"));
                users.add(user);
            }
            rs.close();
        }catch (SQLException e){
            e.getMessage();
        }
        return users;
    }

    public boolean deleteUser(int id){
        DatabaseConnection db = new DatabaseConnection();
        String sql = "DELETE FROM users where id = ?;";
        try{
            PreparedStatement ps = db.getPreparedStatement(sql);
            ps.setInt(1, id);
             return ps.execute();
        }catch (SQLException e){
            e.getMessage();
            return false;
        }
    }

    public boolean editUser(int id,String name, String email, String password, String role){
        DatabaseConnection db = new DatabaseConnection();
        String sql = "UPDATE users SET name = ?, email = ?, password = ?, role = ? where id = ?;";
        try{
            PreparedStatement ps = db.getPreparedStatement(sql);
            ps.setString(1, name);
            ps.setString(2, email);
            ps.setString(3, password);
            ps.setString(4, role);
            ps.setInt(5, id);
            return ps.execute();
        }catch (SQLException e){
            e.getMessage();
            return false;
        }
    }
}
