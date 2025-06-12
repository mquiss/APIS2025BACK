package com.api.ecommerce.product.dto;

import jakarta.validation.constraints.Min;

public record StockRequest(
        @Min(value = 0, message = "stock must be non-negative")
        int stock
) {}