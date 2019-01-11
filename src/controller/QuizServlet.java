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

public class QuizServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String pageRequest = request.getParameter("pageRequest");
        if (pageRequest == null) {
            pageRequest = "start";
        }

        //Request for quiz play page
        if(pageRequest.equals("play")){
            request.getRequestDispatcher("/quiz/play.jsp").forward(request,response);
        }

        //Request for first question
        if(pageRequest.equals("start")){
            int userId = getUserId(request);
            if(new AttemptService().numberOfAttempts(userId) != 0){
                new AttemptService().refreshAttempts(userId);
            }
            int currentRow = 0;
            Question question = new QuestionService().getDisplayableQuestion(currentRow);

            System.out.println(question);
            if(question == null){
                request.setAttribute("message","No questions available");
                request.getRequestDispatcher("/quiz/play.jsp").forward(request,response);
            }
            else{
                currentRow = question.getId();
                redirectToQuestionPage(request, response, currentRow, question);
            }
        }

        //Request for next question
        if(pageRequest.equals("nextQuestion")){
            int userId = getUserId(request);
            int QuestionId = Integer.parseInt(request.getParameter("questionId"));
            int currentRow = Integer.parseInt(request.getParameter("currentRow"));
            String answer =request.getParameter("userAnswer");
            if(answer != null ){
                int userAnswer = Integer.parseInt(answer);
                new AttemptService().insertAttempt(userId,QuestionId,userAnswer);
                currentRow++;
            }
            Question question = new QuestionService().getDisplayableQuestion(currentRow);
            System.out.println(question);
            if(question == null){
                response.sendRedirect("quiz?pageRequest=results");
            }
            else{
                currentRow = question.getId();
                redirectToQuestionPage(request, response, currentRow, question);
            }
        }

        //Request for result page
        if(pageRequest.equals("results")){
            int userId = getUserId(request);
            if(new AttemptService().numberOfAttempts(userId) == 0){
                request.getRequestDispatcher("/quiz/play.jsp").forward(request,response);
            }

            List<Result> results = new AttemptService().getResults(userId);
            int score =0;
            int total =0;
            for (Result result: results){
                total++;
                if(result.getAnswer()==result.getUserAnswer()){
                    score++;
                }
            }
            request.setAttribute("score",score);
            request.setAttribute("total",total);
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

    private void redirectToQuestionPage(HttpServletRequest request, HttpServletResponse response, int currentRow, Question question) throws ServletException, IOException {
        request.setAttribute("question", question);
        request.setAttribute("currentRow", currentRow);
        request.getRequestDispatcher("/quiz/question.jsp").forward(request, response);
    }
}
