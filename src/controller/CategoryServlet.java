package controller;

import domains.Category;
import services.CategoryService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class CategoryServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String pageRequest = request.getParameter("pageRequest");
        if (pageRequest == null) {
            pageRequest = "addCategoryGet";
        }

        //Request for adding category page
        if(pageRequest.equals("addCategoryGet")){
            request.getRequestDispatcher("/question/addCategory.jsp").forward(request,response);
        }

        //Request for adding question in the database and redirecting to registering category add page
        if(pageRequest.equals("addCategoryPost")){
            String name = request.getParameter("name");
            String description = request.getParameter("description");
            String message = new CategoryService().insertCategory(name,description);
            request.setAttribute("message",message);
            request.getRequestDispatcher("/question/addCategory.jsp").forward(request,response);
        }

        //Request for list categories page
        if(pageRequest.equals("listCategories")){
            List<Category> categories = new CategoryService().getAllCategories();
            request.setAttribute("categories",categories);
            request.getRequestDispatcher("/question/listCategories.jsp").forward(request,response);
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            doPost(request,response);
    }
}
