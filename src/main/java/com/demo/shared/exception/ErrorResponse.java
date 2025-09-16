package com.demo.shared.exception;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class ErrorResponse<T> {
    private String message;
    private String status;
    private String path;
    private T details;
    private LocalDateTime timestamp = LocalDateTime.now();

}
