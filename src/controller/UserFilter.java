package controller;

import domains.User;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class UserFilter implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest request = (HttpServletRequest)req;
        HttpSession session = request.getSession(false);
        String pageRequest = request.getParameter("pageRequest");
        if (pageRequest == null){
            pageRequest = "NA";
        }

        //When user tries to access pages that needs authentication without logging in
        if((session == null || session.getAttribute("user")==null) &&
                !pageRequest.equalsIgnoreCase("loginPage") &&
                !pageRequest.equalsIgnoreCase("login") &&
                !pageRequest.equalsIgnoreCase("signup") &&
                !pageRequest.equalsIgnoreCase("register")){

            if(!pageRequest.equalsIgnoreCase("NA")) {
                req.setAttribute("message", "Login first");
            }
            req.getRequestDispatcher("index.jsp").forward(req,resp);
        }

        //When user tries to access login page, login, signup page and register without logging out
        if((session != null && session.getAttribute("user") !=null) &&
                (pageRequest.equalsIgnoreCase("loginPage") ||
                pageRequest.equalsIgnoreCase("login") ||
                pageRequest.equalsIgnoreCase("signup") ||
                pageRequest.equalsIgnoreCase("register"))){
            req.getRequestDispatcher("user/home.jsp").forward(req,resp);
        }


        chain.doFilter(req, resp);
    }

    public void init(FilterConfig config) throws ServletException {

    }

}
