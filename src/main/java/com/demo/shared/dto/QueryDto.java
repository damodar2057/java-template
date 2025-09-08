package com.demo.shared.dto;

import org.springframework.data.domain.Sort;

import lombok.Data;


@Data
public class QueryDto {
    private int page = 0;           // default page 0
    private int pageSize = 10;      // default page size
    private String sortBy = "id";   // default sort field
    private Sort.Direction sortDirection = Sort.Direction.ASC; // default direction
}