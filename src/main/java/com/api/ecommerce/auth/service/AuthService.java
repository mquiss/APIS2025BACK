package com.api.ecommerce.auth.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.api.ecommerce.auth.dto.RegisterRequest;
import com.api.ecommerce.auth.dto.TokenResponse;
import com.api.ecommerce.user.dto.UserResponse;
import com.api.ecommerce.user.service.UserService;
import lombok.RequiredArgsConstructor;
import com.api.ecommerce.common.exception.RecursoNoEncontradoException;
import org.springframework.http.ResponseEntity;


import com.api.ecommerce.auth.dto.LoginRequest;
import com.api.ecommerce.auth.util.JwtUtil;
import com.api.ecommerce.user.model.User;
import com.api.ecommerce.user.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserRepository userRepository;
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    public TokenResponse login(LoginRequest request) {
    User user = userRepository.findByEmail(request.getEmail())
            .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

    if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
        throw new RuntimeException("Credenciales inválidas");
    }

    String accessToken = jwtUtil.generateToken(user.getUsername());
    String refreshToken = jwtUtil.generateRefreshToken(user.getUsername());

    return new TokenResponse(accessToken, refreshToken);
}

    public UserResponse createUser(RegisterRequest registerRequest) {
        return userService.createUser(registerRequest);
    }

 public TokenResponse refreshToken(String refreshToken) {
    try {
        String username = jwtUtil.extractUsername(refreshToken);

        if (!jwtUtil.validateToken(refreshToken, username)) {
            throw new RuntimeException("Refresh token inválido");
        }

        String newAccessToken = jwtUtil.generateToken(username);
        String newRefreshToken = jwtUtil.generateRefreshToken(username);

        return new TokenResponse(newAccessToken, newRefreshToken);

    } catch (Exception e) {
        throw new RuntimeException("No se pudo refrescar el token: " + e.getMessage());
    }
}
}
