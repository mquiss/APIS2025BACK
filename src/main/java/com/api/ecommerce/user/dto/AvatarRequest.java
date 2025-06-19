package com.api.ecommerce.user.dto;

import jakarta.validation.constraints.NotBlank;

public record AvatarRequest(
        @NotBlank(message = "Avatar is required")
        String avatar
) {}