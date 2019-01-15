package services;

import domains.Question;
import domains.Result;
import utils.DatabaseConnection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class QuestionService {
    public String insertQuestion(String question, String optionOne, String optionTwo, String optionThree, String optionFour, int category, int answer, int difficultyLevel, boolean display){
        String message = "";

        DatabaseConnection db = new DatabaseConnection();
        String sql = "INSERT INTO questions(question, optionOne, optionTwo, optionThree, optionFour, category, answer, difficultyLevel, display) VALUES (?,?,?,?,?,?,?,?,?);";
        try{
            PreparedStatement ps = db.getPreparedStatement(sql);
            ps.setString(1, question);
            ps.setString(2, optionOne);
            ps.setString(3, optionTwo);
            ps.setString(4, optionThree);
            ps.setString(5, optionFour);
            ps.setInt(6, category);
            ps.setInt(7, answer);
            ps.setInt(8, difficultyLevel);
            ps.setBoolean(9, display);
            ps.execute();
            message = "Question successfully registered";
        }catch (SQLException e){
            message = e.getMessage();
        }
        return  message;
    }


    public List<Question> getAllQuestions(){
        DatabaseConnection db = new DatabaseConnection();
        String sql = "SELECT * FROM questions";
        List<Question> questions = new ArrayList<Question>();
        try{
            PreparedStatement ps = db.getPreparedStatement(sql);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                Question question = new Question();
                question.setId(rs.getInt("id"));
                question.setQuestion(rs.getString("question"));
                question.setOptionOne(rs.getString("optionOne"));
                question.setOptionTwo(rs.getString("optionTwo"));
                question.setOptionThree(rs.getString("optionThree"));
                question.setOptionFour(rs.getString("optionFour"));
                question.setCategory(rs.getInt("category"));
                question.setAnswer(rs.getInt("answer"));
                question.setDifficultyLevel(rs.getInt("difficultyLevel"));
                question.setDisplay(rs.getBoolean("display"));
                questions.add(question);
            }
            rs.close();
        }catch (SQLException e){
            e.getMessage();
        }
        return questions;
    }

    public Question getQuestion(int id){
        DatabaseConnection db = new DatabaseConnection();
        String sql = "SELECT * FROM questions where id = ?";
        Question question = null;
        try{
            PreparedStatement ps = db.getPreparedStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                question = new Question();
                question.setId(rs.getInt("id"));
                question.setQuestion(rs.getString("question"));
                question.setOptionOne(rs.getString("optionOne"));
                question.setOptionTwo(rs.getString("optionTwo"));
                question.setOptionThree(rs.getString("optionThree"));
                question.setOptionFour(rs.getString("optionFour"));
                question.setCategory(rs.getInt("category"));
                question.setAnswer(rs.getInt("answer"));
                question.setDifficultyLevel(rs.getInt("difficultyLevel"));
                question.setDisplay(rs.getBoolean("display"));
            }
            rs.close();
        }catch (SQLException e){
            e.getMessage();
        }
        return question;
    }

    public String editQuestion(int id, String question, String optionOne, String optionTwo, String optionThree, String optionFour, int category, int answer, int difficultyLevel, boolean display){
        String message = "";

        DatabaseConnection db = new DatabaseConnection();
        String sql = "UPDATE questions SET question = ?, optionOne = ?, optionTwo = ?, optionThree = ?, optionFour = ?, category = ?, answer = ?, difficultyLevel = ?, display = ? WHERE id = ?";
        try{
            PreparedStatement ps = db.getPreparedStatement(sql);
            ps.setString(1, question);
            ps.setString(2, optionOne);
            ps.setString(3, optionTwo);
            ps.setString(4, optionThree);
            ps.setString(5, optionFour);
            ps.setInt(6, category);
            ps.setInt(7, answer);
            ps.setInt(8, difficultyLevel);
            ps.setBoolean(9, display);
            ps.setInt(10, id);
            ps.execute();
            message = "Question with id "+id+" successfully updated";
        }catch (SQLException e){
            message = e.getMessage();
        }
        return  message;
    }

    public String editQuestion(int id, boolean display){
        String message = "";

        DatabaseConnection db = new DatabaseConnection();
        String sql = "UPDATE questions SET display = ? WHERE id = ?";
        try{
            PreparedStatement ps = db.getPreparedStatement(sql);
            ps.setBoolean(1, display);
            ps.setInt(2, id);
            ps.execute();
            if (display){
                message = "Question with id "+id+" will be displayed to users";
            }else{
                message = "Question with id "+id+" will not be displayed to users";
            }

        }catch (SQLException e){
            message = e.getMessage();
        }
        return  message;
    }


    public String deleteQuestion(int id){
        String message = "";

        DatabaseConnection db = new DatabaseConnection();
        String sql = "DELETE FROM questions WHERE id = ?";
        try{
            PreparedStatement ps = db.getPreparedStatement(sql);
            ps.setInt(1, id);
            ps.execute();
            message = "Question with id "+id+" successfully deleted";
        }catch (SQLException e){
            message = e.getMessage();
        }
        return  message;
    }


    public Question getDisplayableQuestion(int row, int category, int difficultyLevel){
        DatabaseConnection db = new DatabaseConnection();
        String sql = "SELECT * FROM (SELECT @row := @row + 1 AS rownum, questions.* FROM (SELECT @row:= 0) AS r, questions WHERE display=true AND category = ? AND difficultyLevel = ?) questions WHERE rownum=?";
        Question question = null;
        try{
            PreparedStatement ps = db.getPreparedStatement(sql);
            ps.setInt(1, category);
            ps.setInt(2, difficultyLevel);
            ps.setInt(3, row);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                question = new Question();
                question.setId(rs.getInt("id"));
                question.setQuestion(rs.getString("question"));
                question.setOptionOne(rs.getString("optionOne"));
                question.setOptionTwo(rs.getString("optionTwo"));
                question.setOptionThree(rs.getString("optionThree"));
                question.setOptionFour(rs.getString("optionFour"));
                question.setCategory(rs.getInt("category"));
                question.setAnswer(rs.getInt("answer"));
                question.setDifficultyLevel(rs.getInt("difficultyLevel"));
                question.setDisplay(rs.getBoolean("display"));
            }
            rs.close();
        }catch (SQLException e){
            e.getMessage();
        }
        return question;
    }
}

