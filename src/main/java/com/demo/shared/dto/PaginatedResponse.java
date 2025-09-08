package com.demo.shared.dto;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
        private int page;
        private int pageSize;
        private long totalElements;
        private int totalPages;
        private boolean last;
    }
    
}

