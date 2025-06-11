package com.api.ecommerce.common.dto;

import java.util.List;

public record PageResponse<T>(
        List<T> content,
        int pageNumber,
        int pageSize,
        long totalElements,
        long totalPages,
        boolean last,
        boolean first
) {}