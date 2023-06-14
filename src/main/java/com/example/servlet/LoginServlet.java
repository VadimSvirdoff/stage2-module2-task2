package com.example.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String user = (String) request.getSession().getAttribute("user");
        if(user != null){
            response.sendRedirect(request.getContextPath() + "/login.jsp");
        }else{
            response.sendRedirect(request.getContextPath() + "/user/hello.jsp");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String password = request.getParameter("password");
        String login = request.getParameter("login");

        if("user".equals(login) && "admin".equals(password)){
            request.getSession().setAttribute("user", login);
            response.sendRedirect(request.getContextPath() + "user/hello.jsp");
        }else {
            request.getRequestDispatcher("/login.jsp").forward(request,response);
        }
    }
}
