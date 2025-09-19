package com.demo.shared.exception;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import jakarta.servlet.http.HttpServletRequest;

@ControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger logger = LogManager.getLogger(GlobalExceptionHandler.class);

    @Value("${spring.app.environment}")
    private String environment;

    // Handle all uncaught exceptions
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse<String>> handleException(Exception ex, HttpServletRequest request) {
        logger.error("Unhandled Exception: ", ex);

        ErrorResponse<String> response = new ErrorResponse<>();
        response.setStatus("error");
        response.setMessage(ex.getMessage());

        // Only include stack trace in development environment
        if ("development".equalsIgnoreCase(environment)) {
            response.setDetails(getStackTraceAsString(ex));
        }

        response.setTimestamp(LocalDateTime.now());
        response.setPath(request.getRequestURI());

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
    }

    // Handle validation errors
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse<List<String>>> handleValidationExceptions(
            MethodArgumentNotValidException ex, HttpServletRequest request) {

        List<String> errors = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(err -> err.getField() + ": " + err.getDefaultMessage())
                .collect(Collectors.toList());

        ErrorResponse<List<String>> response = new ErrorResponse<>();
        response.setStatus("error");
        response.setMessage("Validation failed");
        response.setDetails(errors);
        response.setTimestamp(LocalDateTime.now());
        response.setPath(request.getRequestURI());

        return ResponseEntity.badRequest().body(response);
    }

    @ExceptionHandler(ResourceAlreadyExistsException.class)
    public ResponseEntity<ErrorResponse<String>> handleResourceExists(
            ResourceAlreadyExistsException ex, HttpServletRequest request) {

        logger.warn("ResourceAlreadyExistsException: {}", ex.getMessage());

        ErrorResponse<String> response = new ErrorResponse<>();
        response.setStatus("error");
        response.setMessage(ex.getMessage());
        response.setTimestamp(LocalDateTime.now());
        response.setPath(request.getRequestURI());

        // Optionally include details or stack trace in development
        if ("development".equalsIgnoreCase(environment)) {
            response.setDetails(getStackTraceAsString(ex));
        }

        return ResponseEntity.status(HttpStatus.CONFLICT).body(response);
    }

    // Utility method to convert stack trace to string
    private String getStackTraceAsString(Exception ex) {
        StringWriter sw = new StringWriter();
        ex.printStackTrace(new PrintWriter(sw));
        return sw.toString();
    }
}
