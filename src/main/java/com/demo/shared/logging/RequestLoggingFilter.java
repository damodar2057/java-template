package com.demo.shared.logging;

import java.io.IOException;
import java.util.UUID;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.config.Order;
import org.apache.logging.log4j.ThreadContext;
import org.springframework.stereotype.Component;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
@Order(1)
public class RequestLoggingFilter implements Filter {
    private static final Logger logger = LogManager.getLogger(RequestLoggingFilter.class);

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        // Generate trace ID for request tracking
        String traceId = UUID.randomUUID().toString().substring(0, 8);
        ThreadContext.put("traceId", traceId);

        // Add traceId to response headers
        httpResponse.setHeader("X-Trace-Id", traceId);

        try {
            logger.info("Request: {} {} from {}",
                httpRequest.getMethod(),
                httpRequest.getRequestURI(),
                getClientIpAddress(httpRequest)
            );

            chain.doFilter(httpRequest, httpResponse);
        } finally {
            // Clear ThreadContext
            ThreadContext.clearAll();
        }
    }

    private String getClientIpAddress(HttpServletRequest request) {
        String xForwardedFor = request.getHeader("X-Forwarded-For");
        if (xForwardedFor != null && !xForwardedFor.isEmpty()){
            return xForwardedFor.split(",")[0].trim();
        }

        String xRealIp = request.getHeader("X-Real-IP");
        if (xRealIp != null && !xRealIp.isEmpty()) {
            return xRealIp;
        }
        
        return request.getRemoteAddr();
    }
}
