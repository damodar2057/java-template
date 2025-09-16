package com.demo.shared.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaginatedResponse<T> {
    private List<T> data;
    private Pagination pagination;

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Pagination {
        private int page = 1;
        private int pageSize = 10;
        private long totalElements;
        private int totalPages;
        private boolean last;
    }
}
