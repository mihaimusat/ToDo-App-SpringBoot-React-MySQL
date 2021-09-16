package com.endava.todo.config;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CorsFilter implements Filter {
    private static final String OPTIONS_REQ_TYPE = "OPTIONS";
    private static final String REACT_APP_BASE_URL = "http://localhost:3000";

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        response.setHeader("Access-Control-Allow-Origin", request.getHeader("Origin"));
        response.setHeader("Access-Control-Allow-Credentials", "true");
        response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE, PUT");
        response.setHeader("Access-Control-Max-Age", "3600");
        response.setHeader("Access-Control-Allow-Headers", "Content-Type, Accept, X-Requested-With, remember-me, authorization, x-auth-token");

        if(OPTIONS_REQ_TYPE.equalsIgnoreCase(request.getMethod()) && request.getHeader("Origin").equalsIgnoreCase(REACT_APP_BASE_URL)){
            response.setStatus(HttpServletResponse.SC_OK);
        }
        else{
            filterChain.doFilter(servletRequest, servletResponse);
        }
    }
}
