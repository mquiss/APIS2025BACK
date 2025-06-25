package com.api.ecommerce.auth.controller;

import com.api.ecommerce.auth.dto.LoginRequest;
import com.api.ecommerce.auth.dto.RefreshRequest;
import com.api.ecommerce.auth.dto.RegisterRequest;
import com.api.ecommerce.auth.dto.TokenResponse;
import com.api.ecommerce.user.dto.UserResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.api.ecommerce.auth.service.AuthService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {
    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<TokenResponse> authenticate(@Valid @RequestBody LoginRequest loginRequest) {
        return ResponseEntity.ok(authService.login(loginRequest));
    }

    @PostMapping("/register")
    public ResponseEntity<UserResponse> register(@Valid @RequestBody RegisterRequest registerRequest) {
        return ResponseEntity.ok(authService.createUser(registerRequest));
    }

    @PostMapping("/refresh")
    public ResponseEntity<TokenResponse> refresh(@RequestBody RefreshRequest refreshRequest) {
        TokenResponse response = authService.refreshToken(refreshRequest);
        return ResponseEntity.ok(response);
    }
}
