package com.api.ecommerce.auth.controller;

import com.api.ecommerce.auth.dto.LoginRequest;
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
    public ResponseEntity<TokenResponse> refresh(@RequestBody TokenResponse request) { // TODO: crear un dto TokenRequest o RefreshTokenRequest si solo necesito el refresh token. Validar en el dto con jakarta. No usar response para request body
        TokenResponse response = authService.refreshToken(request.refreshToken());
        return ResponseEntity.ok(response);
    }
}
