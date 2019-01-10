package controller;

import domains.Question;
import domains.Result;
import domains.User;
import services.AttemptService;
import services.QuestionService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

public class QuestionServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String pageRequest = request.getParameter("pageRequest");
        if (pageRequest == null) {
            pageRequest = "postQuestionGet";
        }

        //Request for registering question page
        if(pageRequest.equals("postQuestionGet")){
            request.getRequestDispatcher("/question/postQuestion.jsp").forward(request,response);
        }

        //Request for registering question and redirecting to  registering question page
        if(pageRequest.equals("postQuestionPost")){
            String question = request.getParameter("question");
            String optionone = request.getParameter("optionone");
            String optiontwo = request.getParameter("optiontwo");
            String optionthree = request.getParameter("optionthree");
            String optionfour = request.getParameter("optionfour");
            int answer = Integer.parseInt(request.getParameter("answer"));
            String category = request.getParameter("category");
            int difficultyLevel = Integer.parseInt(request.getParameter("difficulty"));
            boolean display = Boolean.parseBoolean(request.getParameter("display"));
            String message = new QuestionService().insertQuestion(question,optionone,optiontwo,optionthree,optionfour,category,answer,difficultyLevel, display);
            request.setAttribute("message", message);
            request.getRequestDispatcher("/question/postQuestion.jsp").forward(request,response);
        }

        //Request for list of questions page
        if(pageRequest.equals("listQuestions")){
            redirectToListQuestions(request, response, "");
        }

        //Request for editing question page
        if(pageRequest.equals("editQuestionGet")){
            int id = Integer.parseInt(request.getParameter("id"));
            Question question = new QuestionService().getQuestion(id);
            request.setAttribute("question", question);
            request.getRequestDispatcher("/question/editQuestion.jsp").forward(request,response);
        }

        //Request for editing data of a question and redirecting to list of questions page
        if(pageRequest.equals("editQuestionPost")){
            int id = Integer.parseInt(request.getParameter("id"));
            String question = request.getParameter("question");
            String optionone = request.getParameter("optionone");
            String optiontwo = request.getParameter("optiontwo");
            String optionthree = request.getParameter("optionthree");
            String optionfour = request.getParameter("optionfour");
            int answer = Integer.parseInt(request.getParameter("answer"));
            String category = request.getParameter("category");
            int difficultyLevel = Integer.parseInt(request.getParameter("difficulty"));
            boolean display = Boolean.parseBoolean(request.getParameter("display"));
            String message = new QuestionService().editQuestion(id,question,optionone,optiontwo,optionthree,optionfour,category,answer,difficultyLevel, display);
            redirectToListQuestions(request, response, message);
        }

        //Request for deleting a question and redirecting to list of questions page
        if(pageRequest.equals("deleteQuestion")){
            int id = Integer.parseInt(request.getParameter("id"));
            String message = new QuestionService().deleteQuestion(id);
            redirectToListQuestions(request, response, message);

        }

        if(pageRequest.equals("editDisplay")){
            int id = Integer.parseInt(request.getParameter("id"));
            boolean display = Boolean.parseBoolean(request.getParameter("display"));
            String message = new QuestionService().editQuestion(id, display);
            redirectToListQuestions(request,response,message);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }

    private void redirectToListQuestions(HttpServletRequest request, HttpServletResponse response, String message) throws ServletException, IOException {
        List<Question> questions = new QuestionService().getAllQuestions();
        request.setAttribute("message", message);
        request.setAttribute("questions", questions);
        request.getRequestDispatcher("/question/listQuestions.jsp").forward(request, response);
    }
}
