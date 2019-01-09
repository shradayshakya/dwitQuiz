package controller;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;
import services.UserService;
import domains.User;


public class UserServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String pageRequest = request.getParameter("pageRequest");
        if (pageRequest == null) {
            pageRequest = "home";
        }


        if(pageRequest.equals("loginPage")){
            request.getRequestDispatcher("index.jsp").forward(request,response);
        }

        //Request from login page
        if(pageRequest.equals("login")){
            String email = request.getParameter("email");
            String password = request.getParameter("password");
            User user = new UserService().getUser(email, password);
            if(user != null){
                HttpSession session = request.getSession(false);
                session = request.getSession(false);
                session.setAttribute("user",user);
                request.getRequestDispatcher("user/home.jsp").forward(request,response);

            }else{
                request.setAttribute("message", "Invalid email and password");
                request.getRequestDispatcher("/index.jsp").forward(request,response);
            }
        }


        //Request for logout
        if(pageRequest.equals("logout")){
            HttpSession session = request.getSession(false);
            session.invalidate();

            request.setAttribute("message", "Logged out");
            request.getRequestDispatcher("/index.jsp").forward(request,response);
        }


        //Request for signup page
        if(pageRequest.equals("signup")){
            request.getRequestDispatcher("signup.jsp").forward(request,response);
        }


        //Request to register user
        if(pageRequest.equals("register")){
            String email = request.getParameter("email");
            String name = request.getParameter("name");
            String password =request.getParameter("password");
            String message = new UserService().insertUser(name, email,password);
            request.setAttribute("message", message);
            request.getRequestDispatcher("/index.jsp").forward(request,response);
        }

        //Request for homepage
        if(pageRequest.equals("home")){
                request.getRequestDispatcher("/user/home.jsp").forward(request,response);
        }

        //Request for list of users page
        if(pageRequest.equals("listUsers")){
            redirectToListUsers(request, response);
        }

        //Request for deleting user and redirecting to list of users page
        if(pageRequest.equals("deleteUser")){
            int id = Integer.parseInt(request.getParameter("id"));
            request.setAttribute("message", "User with id:"+id+" deleted");
            new UserService().deleteUser(id);
            redirectToListUsers(request, response);
        }

        //Request for edit user page
        if(pageRequest.equals("editUserGet")){
            int id = Integer.parseInt(request.getParameter("id"));
            User user = new UserService().getUser(id);
            request.setAttribute("user",user);
            request.getRequestDispatcher("/user/editUser.jsp").forward(request,response);
        }

        //Request for updating data of user and redirecting to list of users page
        if(pageRequest.equals("editUserPost")){
            int id = Integer.parseInt(request.getParameter("id"));
            String name = request.getParameter("name");
            String email = request.getParameter("email");
            String password = request.getParameter("password");
            String role = request.getParameter("role");
            request.setAttribute("message", "User with id:"+id+" updated");
            new UserService().editUser(id,name,email,password,role);
            redirectToListUsers(request, response);
        }
    }

    private void redirectToLogin(HttpServletRequest request, HttpServletResponse response, User user) throws ServletException, IOException {
        if (user==null) {
            request.setAttribute("message","Please login!!");
            RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
            rd.forward(request, response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }




    private void redirectToListUsers(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<User> userList = new UserService().getAllUsers();
        request.setAttribute("users", userList);
        request.getRequestDispatcher("/user/listUsers.jsp").forward(request, response);
    }

}
