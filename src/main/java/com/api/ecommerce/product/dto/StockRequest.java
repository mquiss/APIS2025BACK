package com.api.ecommerce.product.dto;

import jakarta.validation.constraints.Min;

public record StockRequest(
        @Min(0) int stock
) {}