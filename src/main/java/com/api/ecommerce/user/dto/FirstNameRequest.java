package com.api.ecommerce.user.dto;

import jakarta.validation.constraints.NotBlank;

public record FirstNameRequest(
        @NotBlank (message = "First name is required")
        String firstName
) {}