package com.api.ecommerce.auth.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class RegisterRequest {
    @NotBlank (message = "firstName is required")
    private String firstName;
    @NotBlank (message = "lastName is required")
    private String lastName;
    @Email @NotBlank (message = "email is required")
    private String email;
    @NotBlank (message = "password is required")
    private String password;
}
