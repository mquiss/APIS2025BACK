package com.api.ecommerce.product.dto;

import jakarta.validation.constraints.Min;

public record StockRequest(
        @Min(value = 0, message = "Stock must be non-negative")
        int stock
) {}