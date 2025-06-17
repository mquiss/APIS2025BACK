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
        User user = userRepository.findByEmail(request.getEmail()).orElseThrow(RecursoNoEncontradoException::new);

        if (passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            String token = jwtUtil.generateToken(user.getUsername());

            return new TokenResponse(token, "refresh token, falta implementar");
        } else {
            throw new RuntimeException("credenciales incorrectas");
        }
    }

    public UserResponse createUser(RegisterRequest registerRequest) {
        return userService.createUser(registerRequest);
    }

    public void refreshToken(String authHeader) {
    }
}
