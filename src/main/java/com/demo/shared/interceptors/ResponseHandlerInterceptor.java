package com.demo.shared.interceptors;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class ResponseHandlerInterceptor implements HandlerInterceptor {
    
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public boolean preHandle(
        HttpServletRequest request,
        HttpServletResponse response,
        Object handler
    ) throws Exception {
        // Pre-Processing before controller method execution
        System.out.println("Pre Handler: " + request.getRequestURI());

        // Add custom header
        response.setHeader("X-custom-header", "CustomValue");
        return true; // Continue with the request
    }

    @Override
    public void postHandle(
        HttpServletRequest request,
        HttpServletResponse response,
        Object handler,
        ModelAndView modelAndView) throws Exception
    {
        // Logic to execute after controller method but before response is sent

        
    }

        @Override
        public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler,
                                     Exception ex) throws Exception {
            // Logic to execute after request completion, even if an exception occurred
            if (ex != null) {
                System.err.println("Request completed with exception: " + ex.getMessage());
            }
            System.out.println("Response intercepted in afterCompletion!");
        }
}

