package com.demo.shared.dto;

import java.time.LocalDateTime;

import com.demo.shared.dto.PaginatedResponse.Pagination;

import lombok.Data;

@Data
public class ApiResponse<T> {

    private String status;
    private String message;
    private T data;
    private LocalDateTime timestamp = LocalDateTime.now();
    private String path;
    private Pagination pagination;

}
