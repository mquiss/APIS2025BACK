package com.api.ecommerce.auth.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.api.ecommerce.auth.dto.RegisterRequest;
import com.api.ecommerce.user.dto.UserResponse;
import com.api.ecommerce.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;


import com.api.ecommerce.auth.dto.LoginRequest;
import com.api.ecommerce.auth.util.JwtUtil;
import com.api.ecommerce.user.dto.UserDTO;
import com.api.ecommerce.user.model.User;
import com.api.ecommerce.user.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserRepository userRepository;
    private final UserService userService;
    private final JwtUtil jwtUtil;

    public ResponseEntity<?> login(LoginRequest request) {
        if (request.getUsername() == null || request.getPassword() == null) {
            return ResponseEntity.badRequest().body("Credenciales incompletas");
        }

        List<User> usuarios = userRepository.findByUsernameAndPassword(request.getUsername(), request.getPassword());

        if (!usuarios.isEmpty()) {
            User user = usuarios.get(0);
            String token = jwtUtil.generateToken(user.getUsername());

            Map<String, Object> response = new HashMap<>();
            response.put("token", token);
            response.put("user", user);

            System.out.println("Usuario autenticado: " + user.getUsername());
            System.out.println("Token generado: " + token);

            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.status(401).body("Credenciales inválidas");
        }
    }

    public UserResponse createUser(RegisterRequest registerRequest) { // debe retornar un usuario?
        return userService.createUser(registerRequest, jwtUtil.getPasswordEncoder());
    }
}
