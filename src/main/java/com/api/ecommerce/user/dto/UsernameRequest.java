package com.api.ecommerce.user.dto;

import jakarta.validation.constraints.NotBlank;

public record UsernameRequest (
        @NotBlank(message = "Username is required")
        String username
) {}
