// package com.demo.shared.exception;

// import org.springframework.http.HttpStatus;
// import org.springframework.http.ResponseEntity;
// import org.springframework.web.bind.annotation.ExceptionHandler;
// import org.springframework.web.bind.annotation.RestControllerAdvice;
// import org.springframework.web.server.ResponseStatusException;

// @RestControllerAdvice
// public class GlobalExceptionHandler {

//     @ExceptionHandler(ResponseStatusException.class)
//     public ResponseEntity<ErrorResponse> handleResponseStatusException(ResponseStatusException ex) {
//         ErrorResponse error = new ErrorResponse(
//                 ex.getReason() != null ? ex.getReason() : "Error",
//                 ex.getStatusCode().value());
//         return new ResponseEntity<>(error, ex.getStatusCode());
//     }

//     @ExceptionHandler(Exception.class)
//     public ResponseEntity<ErrorResponse> handleGenericException(Exception ex) {
//         ErrorResponse error = new ErrorResponse("Something went wrong", HttpStatus.INTERNAL_SERVER_ERROR.value());
//         return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
//     }
// }
