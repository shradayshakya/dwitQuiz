package services;

import domains.Result;
import utils.DatabaseConnection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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

    public int numberOfAttempts(int userId, int categoryId, int difficultyLevel){
        DatabaseConnection db = new DatabaseConnection();
        String sql = "SELECT count(attempts.id) as total FROM attempts INNER join questions q on attempts.questionId = q.id where userId = ? AND category = ? AND difficultyLevel = ?;";
        int attempts =0;
        try{
            PreparedStatement ps = db.getPreparedStatement(sql);
            ps.setInt(1, userId);
            ps.setInt(2, categoryId);
            ps.setInt(3, difficultyLevel);
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

    public String refreshAttempts(int userId , int categoryId, int difficultyLevel){
        String message = "";

        DatabaseConnection db = new DatabaseConnection();
        String sql = "DELETE FROM attempts where id IN (select id FROM(select attempts.id from attempts inner join questions q on attempts.questionId = q.id where userId =? AND category=? and difficultyLevel=?) AS atmp);;";
        try{
            PreparedStatement ps = db.getPreparedStatement(sql);
            ps.setInt(1, userId);
            ps.setInt(2, categoryId);
            ps.setInt(3, difficultyLevel);
            ps.execute();
            message = "Attempts deleted";
        }catch (SQLException e){
            message = e.getMessage();
        }
        return  message;
    }


    public List<Result> getResults(int id){
        DatabaseConnection db = new DatabaseConnection();
        String sql = "SELECT attempts.id, questions.question, " +
                " questions.optionOne, questions.optionTwo, " +
                " questions.optionThree, questions.optionFour, " +
                "questions.answer, questions.category," +
                "questions.difficultyLevel, attempts.userAnswer " +
                "FROM questions " +
                "INNER JOIN attempts " +
                "ON " +
                "attempts.questionId=questions.id " +
                "WHERE attempts.userId = ?";
        List<Result> results= new ArrayList<Result>();
        try{
            PreparedStatement ps = db.getPreparedStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                Result result = new Result();
                result.setId(rs.getInt("id"));
                result.setUserId(id);
                result.setQuestion(rs.getString("question"));
                result.setOptionOne(rs.getString("optionOne"));
                result.setOptionTwo(rs.getString("optionTwo"));
                result.setOptionThree(rs.getString("optionThree"));
                result.setOptionFour(rs.getString("optionFour"));
                result.setAnswer(rs.getInt("answer"));
                result.setUserAnswer(rs.getInt("userAnswer"));
                result.setCategory(rs.getInt("category"));
                result.setDifficultyLevel(rs.getInt("difficultyLevel"));
                results.add(result);
            }
            rs.close();
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return results;
    }
}
