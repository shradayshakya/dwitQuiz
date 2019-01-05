package services;

import utils.DatabaseConnection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AttemptService {
    public String insertAttempt(int userId, int questionId, int userAnswer){
        String message = "";

        DatabaseConnection db = new DatabaseConnection();
        String sql = "INSERT INTO attempts(userId, questionId, userAnswer) VALUES (?,?,?);";
        try{
            PreparedStatement ps = db.getPreparedStatement(sql);
            ps.setInt(1, userId);
            ps.setInt(2, questionId);
            ps.setInt(3, userAnswer);
            ps.execute();
            message = "Attempt successfully registered";
        }catch (SQLException e){
            message = e.getMessage();
        }
        return  message;
    }
    public int numberOfAttempts(int userId){
        DatabaseConnection db = new DatabaseConnection();
        String sql = "SELECT count(*) AS total from attempts where userId = ?;";
        int attempts =0;
        try{
            PreparedStatement ps = db.getPreparedStatement(sql);
            ps.setInt(1, userId);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                attempts = rs.getInt("total");
            }
            rs.close();
        }catch (SQLException e){
            e.getMessage();
        }
        return attempts;
    }

    public String refreshAttempts(int userId){
        String message = "";

        DatabaseConnection db = new DatabaseConnection();
        String sql = "DELETE FROM attempts where userId = ?";
        try{
            PreparedStatement ps = db.getPreparedStatement(sql);
            ps.setInt(1, userId);
            ps.execute();
            message = "Attempts deleted";
        }catch (SQLException e){
            message = e.getMessage();
        }
        return  message;
    }
}
