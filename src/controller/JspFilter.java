package controller;

import com.sun.deploy.net.HttpRequest;
import com.sun.deploy.net.HttpResponse;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(filterName = "JspFilter")
public class JspFilter implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest request = (HttpServletRequest)req;
        String uri = request.getRequestURI();
        if(uri.endsWith("/") || uri.endsWith("/index.jsp")){
            chain.doFilter(req, resp);
        }else{
            resp.getWriter().println("Page not found");
        }
    }

    public void init(FilterConfig config) throws ServletException {

    }

}
