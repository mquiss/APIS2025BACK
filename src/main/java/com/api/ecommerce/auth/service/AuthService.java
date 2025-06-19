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
        // Validar que el request no sea nulo
        if (request == null || request.getEmail() == null || request.getPassword() == null) {
            throw new IllegalArgumentException("Credenciales incompletas");
        }

        try {
            User user = userRepository.findByEmail(request.getEmail())
                    .orElseThrow(() -> new RecursoNoEncontradoException("Usuario con email " + request.getEmail() + " no encontrado"));
            if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
                throw new SecurityException("Contraseña incorrecta");
            }

            String accessToken = jwtUtil.generateToken(user.getUsername());
            String refreshToken = jwtUtil.generateRefreshToken(user.getUsername());

            return new TokenResponse(accessToken, refreshToken);
        } catch (RecursoNoEncontradoException e) {
            throw e;
        } catch (SecurityException e) {
            throw e;
        } catch (Exception e) {
            throw new RuntimeException("Error en el proceso de login: " + e.getMessage());
        }
    }

    public UserResponse createUser(RegisterRequest registerRequest) {
        // Validar que el request no sea nulo
        if (registerRequest == null) {
            throw new IllegalArgumentException("La solicitud de registro no puede ser nula");
        }

        // Validar campos obligatorios
        if (registerRequest.getEmail() == null || registerRequest.getEmail().isEmpty()) {
            throw new IllegalArgumentException("El email es obligatorio");
        }

        if (registerRequest.getPassword() == null || registerRequest.getPassword().length() < 6) {
            throw new IllegalArgumentException("La contraseña debe tener al menos 6 caracteres");
        }

        // Verificar si el usuario ya existe
        if (userRepository.findByEmail(registerRequest.getEmail()).isPresent()) {
            throw new IllegalArgumentException("El email ya está registrado");
        }

        try {
            return userService.createUser(registerRequest);
        } catch (Exception e) {
            throw new RuntimeException("Error al crear el usuario: " + e.getMessage());
        }
    }

    public TokenResponse refreshToken(String refreshToken) {
        // Validar que el token no sea nulo o vacío
        if (refreshToken == null || refreshToken.isEmpty()) {
            throw new IllegalArgumentException("El token de refresco no puede ser nulo o vacío");
        }

        try {
            String username = jwtUtil.extractUsername(refreshToken);

            if (username == null || username.isEmpty()) {
                throw new SecurityException("No se pudo extraer el username del token");
            }

            if (!jwtUtil.validateToken(refreshToken, username)) {
                throw new SecurityException("Refresh token inválido o expirado");
            }

            String newAccessToken = jwtUtil.generateToken(username);
            String newRefreshToken = jwtUtil.generateRefreshToken(username);

            return new TokenResponse(newAccessToken, newRefreshToken);
        } catch (SecurityException e) {
            throw e;
        } catch (Exception e) {
            throw new RuntimeException("Error al refrescar el token: " + e.getMessage());
        }
    }
}

