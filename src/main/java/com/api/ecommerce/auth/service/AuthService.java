package com.api.ecommerce.auth.service;

import java.time.Instant;

import com.api.ecommerce.auth.dto.RegisterRequest;
import com.api.ecommerce.auth.dto.TokenResponse;
import com.api.ecommerce.user.dto.UserResponse;
import com.api.ecommerce.user.service.UserService;
import lombok.RequiredArgsConstructor;


import com.api.ecommerce.auth.dto.LoginRequest;
import com.api.ecommerce.auth.util.JwtUtil;
import com.api.ecommerce.common.exception.RecursoNoEncontradoException;
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

    if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
        throw new RuntimeException("Credenciales inválidas");
    }

    String accessToken = jwtUtil.generateToken(user.getUsername());
    String refreshToken = jwtUtil.generateToken(user.getUsername());
    String userId = user.getId().toString();
    String username = user.getUsername();
    String email = user.getEmail();
    String firstName = user.getFirstName();
    String lastName = user.getLastName();
    String avatar = user.getAvatar();
    Instant createdAt = user.getCreatedAt();
    Instant updatedAt = user.getUpdatedAt();



    return new TokenResponse(accessToken, refreshToken,
            userId, username, email, firstName, lastName, avatar, createdAt, updatedAt);
}

    public UserResponse createUser(RegisterRequest registerRequest) {
        return userService.createUser(registerRequest);
    }

 public TokenResponse refreshToken(String refreshToken) {
        try {
            User user = jwtUtil.getUserFromToken(refreshToken,userRepository);
            if (user == null) {
                throw new RuntimeException("Usuario no encontrado");
            }

        String newaAccessToken = jwtUtil.generateToken(user.getUsername());
        String newRefreshToken = jwtUtil.generateToken(user.getUsername());
        String userId = user.getId().toString();
        String username = user.getUsername();
        String email = user.getEmail();
        String firstName = user.getFirstName();
        String lastName = user.getLastName();
        String avatar = user.getAvatar();
        Instant createdAt = user.getCreatedAt();
        Instant updatedAt = user.getUpdatedAt();



        return new TokenResponse(newaAccessToken, newRefreshToken,
                userId, username, email, firstName, lastName, avatar, createdAt, updatedAt);


        } catch (Exception e) {
            throw new RuntimeException("No se pudo refrescar el token: " + e.getMessage());
        }
    }
}
