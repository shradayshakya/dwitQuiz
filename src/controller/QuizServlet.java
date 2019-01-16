package controller;

import domains.Category;
import domains.Question;
import domains.Result;
import domains.User;
import services.AttemptService;
import services.CategoryService;
import services.QuestionService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

public class QuizServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String pageRequest = request.getParameter("pageRequest");
        if (pageRequest == null) {
            pageRequest = "play";
        }

        //Request for quiz play page
        if(pageRequest.equals("play")){
            List<Category> categories = new CategoryService().getAllCategories();
            request.setAttribute("categories",categories);
            request.getRequestDispatcher("/quiz/play.jsp").forward(request,response);
        }

        //Request for first question
        if(pageRequest.equals("start")){
            int categoryId = Integer.parseInt(request.getParameter("categoryId"));
            int difficultyLevel = Integer.parseInt(request.getParameter(request.getParameter("categoryName")));
            int userId = getUserId(request);
            int attempts;
            if( (new AttemptService().numberOfAttempts(userId,categoryId,difficultyLevel)) != 0){
                new AttemptService().refreshAttempts(userId,categoryId,difficultyLevel);
            }
            int currentRow = 1;
            Question question = new QuestionService().getDisplayableQuestion(currentRow, categoryId,difficultyLevel);
            if(question == null){
                request.setAttribute("message","No questions available");
                request.getRequestDispatcher("quiz?pageRequest=play").forward(request,response);
            }
            else{
                redirectToQuestionPage(request, response, currentRow, question,categoryId,difficultyLevel);
            }
        }

        //Request for next question
        if(pageRequest.equals("nextQuestion")){
            int categoryId = Integer.parseInt(request.getParameter("categoryId"));
            int difficultyLevel = Integer.parseInt(request.getParameter("difficultyLevel"));
            int userId = getUserId(request);
            int QuestionId = Integer.parseInt(request.getParameter("questionId"));
            int currentRow = Integer.parseInt(request.getParameter("currentRow"));
            String answer =request.getParameter("userAnswer");
            if(answer != null ){
                int userAnswer = Integer.parseInt(answer);
                new AttemptService().insertAttempt(userId,QuestionId,userAnswer);
                currentRow++;
            }
            Question question = new QuestionService().getDisplayableQuestion(currentRow, categoryId, difficultyLevel);
            if(question == null){
                response.sendRedirect("quiz?pageRequest=results");
            }
            else{
                redirectToQuestionPage(request, response, currentRow, question, categoryId, difficultyLevel);
            }
        }

        //Request for result page
        if(pageRequest.equals("results")){
            int userId = getUserId(request);
            if(new AttemptService().numberOfAttempts(userId) == 0){
                request.setAttribute("message","Play a quiz first to view results");
                request.getRequestDispatcher("/quiz?pageRequest=play").forward(request,response);
            }

            List<Result> results = new AttemptService().getResults(userId);
            List<Category> categories = new CategoryService().getAllCategories();
            request.setAttribute("categories",categories);
            request.setAttribute("results",results);
            request.getRequestDispatcher("/quiz/results.jsp").forward(request,response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }

    private int getUserId(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        User user = (User) session.getAttribute("user");
        return user.getId();
    }

    private void redirectToQuestionPage(HttpServletRequest request, HttpServletResponse response, int currentRow, Question question, int categoryId, int difficultyLevel) throws ServletException, IOException {
        request.setAttribute("categoryId",categoryId);
        request.setAttribute("difficultyLevel",difficultyLevel);
        request.setAttribute("question", question);
        request.setAttribute("currentRow", currentRow);
        request.getRequestDispatcher("/quiz/question.jsp").forward(request, response);
    }
}
