package com.api.ecommerce.auth.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class RegisterRequest {
    @NotBlank (message = "First name is required")
    private String firstName;
    @NotBlank (message = "Last name is required")
    private String lastName;
    @Email @NotBlank (message = "Email is required")
    private String email;
    @NotBlank (message = "Password is required")
    private String password;
}
