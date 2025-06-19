package com.api.ecommerce.user.dto;

import jakarta.validation.constraints.NotBlank;

public record LastNameRequest(
        @NotBlank (message = "Last name is required")
        String lastName
) {}
