package controller;

import domains.Question;
import domains.Result;
import services.AttemptService;
import services.QuestionService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class QuestionServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String pageRequest = request.getParameter("pageRequest");
        if (pageRequest == null) {
            pageRequest = "play";
        }

        //Request for registering question page
        if(pageRequest.equals("postQuestionGet")){
            request.getRequestDispatcher("/user/postQuestion.jsp").forward(request,response);
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
            String message = new QuestionService().insertQuestion(question,optionone,optiontwo,optionthree,optionfour,category,answer,difficultyLevel);
            request.setAttribute("message", message);
            request.getRequestDispatcher("/user/postQuestion.jsp").forward(request,response);
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
            request.getRequestDispatcher("/user/editQuestion.jsp").forward(request,response);
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
            String message = new QuestionService().editQuestion(id,question,optionone,optiontwo,optionthree,optionfour,category,answer,difficultyLevel);
            redirectToListQuestions(request, response, message);
        }

        //Request for deleting a question and redirecting to list of questions page
        if(pageRequest.equals("deleteQuestion")){
            int id = Integer.parseInt(request.getParameter("id"));
            String message = new QuestionService().deleteQuestion(id);
            redirectToListQuestions(request, response, message);

        }



        //Request for quiz play page
        if(pageRequest.equals("play")){
            request.getRequestDispatcher("/user/play.jsp").forward(request,response);
        }

        //Request for first question
        if(pageRequest.equals("start")){
            int userId = Integer.parseInt(request.getParameter("userId"));
            if(new AttemptService().numberOfAttempts(userId) != 0){
                new AttemptService().refreshAttempts(userId);
            }
            int currentRow = 1;
            requestQuestion(request, response, currentRow);
        }

        //Request for next question, maximum of 5 questions
        if(pageRequest.equals("nextQuestion")){
            int userId = Integer.parseInt(request.getParameter("userId"));
            int QuestionId = Integer.parseInt(request.getParameter("questionId"));
            int userAnswer = Integer.parseInt(request.getParameter("userAnswer"));
            int currentRow = Integer.parseInt(request.getParameter("currentRow"));
            System.out.println(new AttemptService().insertAttempt(userId,QuestionId,userAnswer));
            if(currentRow >= 1 && currentRow < 5){
                currentRow++;
                requestQuestion(request, response, currentRow);
            }
            else{
                request.getRequestDispatcher("/user/play.jsp").forward(request,response);
            }
        }

        //Request for result page
        if(pageRequest.equals("results")){
            int userId = Integer.parseInt(request.getParameter("userId"));
            if(new AttemptService().numberOfAttempts(userId) == 0){
                request.getRequestDispatcher("/user/play.jsp").forward(request,response);
            }

            List<Result> results = new QuestionService().getResults(userId);
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
            request.getRequestDispatcher("/user/results.jsp").forward(request,response);
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }



    private void requestQuestion(HttpServletRequest request, HttpServletResponse response, int currentRow) throws ServletException, IOException {
        Question question = new QuestionService().getQuestion(5, currentRow);
        if(question == null){
            response.sendRedirect("user?pageRequest=play");
        }
        else{
            request.setAttribute("question", question);
            request.setAttribute("currentRow", currentRow);
            request.getRequestDispatcher("/user/question.jsp").forward(request, response);
        }
    }


    private void redirectToListQuestions(HttpServletRequest request, HttpServletResponse response, String message) throws ServletException, IOException {
        List<Question> questions = new QuestionService().getAllQuestions();
        request.setAttribute("message", message);
        request.setAttribute("questions", questions);
        request.getRequestDispatcher("/user/listQuestions.jsp").forward(request, response);
    }

}
