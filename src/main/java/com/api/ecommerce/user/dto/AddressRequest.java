package com.api.ecommerce.user.dto;

import jakarta.validation.constraints.NotBlank;

public record AddressRequest(
        @NotBlank(message = "Street cannot be blank") String street,
        @NotBlank(message = "State cannot be blank") String state,
        @NotBlank(message = "Country cannot be blank") String country
) {}
