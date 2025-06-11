package com.api.ecommerce.auth.controller;

import lombok.Data;

@Data
public class LoginRequest {
    private String username;
    private String password;
}
