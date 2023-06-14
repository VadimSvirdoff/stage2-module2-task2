package com.example.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(urlPatterns = {"/user/*"})
public class AuthFilter implements Filter {
    private String urlPatterns;

    @Override
    public void init(FilterConfig filterConfig) {
        urlPatterns = filterConfig.getInitParameter("urlPatterns");
    }
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        if (request != null && response != null) {
            String path = request.getRequestURI();
            String user = (String) request.getSession().getAttribute("user");

            if (path.startsWith(urlPatterns) && user == null) {
                response.sendRedirect(request.getContextPath() + "/login.jsp");
            } else {
                chain.doFilter(request, response);
            }
        }
    }
}