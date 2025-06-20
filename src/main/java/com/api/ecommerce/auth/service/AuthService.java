package com.api.ecommerce.auth.service;

import com.api.ecommerce.auth.dto.RegisterRequest;
import com.api.ecommerce.auth.dto.TokenResponse;
import com.api.ecommerce.common.exception.ErrorCreacionException;
import com.api.ecommerce.user.dto.UserResponse;
import com.api.ecommerce.user.service.UserService;
import lombok.RequiredArgsConstructor;
import com.api.ecommerce.common.exception.RecursoNoEncontradoException;

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
        try {
            User user = userRepository.findByEmail(request.getEmail())
                    .orElseThrow(() -> new RecursoNoEncontradoException("Usuario con email " + request.getEmail() + " no encontrado"));
            if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
                throw new SecurityException("Contraseña incorrecta");
            }

            String accessToken = jwtUtil.generateToken(user.getUsername());
            String refreshToken = jwtUtil.generateRefreshToken(user.getUsername());

            return new TokenResponse(accessToken, refreshToken);
        } catch (RecursoNoEncontradoException | SecurityException e) {
            throw e;
        } catch (Exception e) {
            throw new RuntimeException("Error en el proceso de login: " + e.getMessage());
        }
    }

    public UserResponse createUser(RegisterRequest registerRequest) {
        try {
            return userService.createUser(registerRequest);
        } catch (Exception e) {
            throw new ErrorCreacionException("Error al crear el usuario: " + e.getMessage());
        }
    }

    public TokenResponse refreshToken(String refreshToken) {
        if (refreshToken == null || refreshToken.isEmpty()) { // TODO: se valida en controller con jakarta, cuando este el dto para este request
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

