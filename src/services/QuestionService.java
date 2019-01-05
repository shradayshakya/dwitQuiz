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
    public String insertQuestion(String question, String optionOne, String optionTwo, String optionThree, String optionFour, String category, int answer, int difficultyLevel){
        String message = "";

        DatabaseConnection db = new DatabaseConnection();
        String sql = "INSERT INTO questions(question, optionOne, optionTwo, optionThree, optionFour, category, answer, difficultyLevel) VALUES (?,?,?,?,?,?,?,?);";
        try{
            PreparedStatement ps = db.getPreparedStatement(sql);
            ps.setString(1, question);
            ps.setString(2, optionOne);
            ps.setString(3, optionTwo);
            ps.setString(4, optionThree);
            ps.setString(5, optionFour);
            ps.setString(6, category);
            ps.setInt(7, answer);
            ps.setInt(8, difficultyLevel);
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
                question.setCategory(rs.getString("category"));
                question.setAnswer(rs.getInt("answer"));
                question.setDifficultyLevel(rs.getInt("difficultyLevel"));
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
                question.setCategory(rs.getString("category"));
                question.setAnswer(rs.getInt("answer"));
                question.setDifficultyLevel(rs.getInt("difficultyLevel"));
            }
            rs.close();
        }catch (SQLException e){
            e.getMessage();
        }
        return question;
    }

    public String editQuestion(int id, String question, String optionOne, String optionTwo, String optionThree, String optionFour, String category, int answer, int difficultyLevel){
        String message = "";

        DatabaseConnection db = new DatabaseConnection();
        String sql = "UPDATE questions SET question = ?, optionOne = ?, optionTwo = ?, optionThree = ?, optionFour = ?, category = ?, answer = ?, difficultyLevel = ? WHERE id = ?";
        try{
            PreparedStatement ps = db.getPreparedStatement(sql);
            ps.setString(1, question);
            ps.setString(2, optionOne);
            ps.setString(3, optionTwo);
            ps.setString(4, optionThree);
            ps.setString(5, optionFour);
            ps.setString(6, category);
            ps.setInt(7, answer);
            ps.setInt(8, difficultyLevel);
            ps.setInt(9, id);
            ps.execute();
            message = "Question with id "+id+" successfully updated";
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


    public Question getQuestion(int limit, int row){
        Question[] questions = new Question[limit];
        DatabaseConnection db = new DatabaseConnection();
        String sql = "SELECT * FROM questions LIMIT ?";

        try{
            PreparedStatement ps = db.getPreparedStatement(sql);
            ps.setInt(1, limit);
            ResultSet rs = ps.executeQuery();
            int i = 0;
            while(i < 5 && rs.next()){
                Question question = new Question();
                question.setId(rs.getInt("id"));
                question.setQuestion(rs.getString("question"));
                question.setOptionOne(rs.getString("optionOne"));
                question.setOptionTwo(rs.getString("optionTwo"));
                question.setOptionThree(rs.getString("optionThree"));
                question.setOptionFour(rs.getString("optionFour"));
                question.setCategory(rs.getString("category"));
                question.setAnswer(rs.getInt("answer"));
                question.setDifficultyLevel(rs.getInt("difficultyLevel"));
                questions[i] = question;
                i++;
            }
            rs.close();
        }catch (SQLException e){
            e.getMessage();
        }
        try {
            return questions[row - 1];
        }catch (Exception e){
            return null;
        }
    }

    public List<Result> getResults(int id){
        DatabaseConnection db = new DatabaseConnection();
        String sql = "SELECT attempts.id, questions.question, " +
                            " questions.optionOne, questions.optionTwo, " +
                            " questions.optionThree, questions.optionFour, " +
                            "questions.answer, attempts.userAnswer " +
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
                results.add(result);
            }
            rs.close();
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return results;
    }
}
