package com.api.ecommerce.auth.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;


import com.api.ecommerce.auth.controller.LoginRequest;
import com.api.ecommerce.auth.util.JwtUtil;
import com.api.ecommerce.user.dto.UserDTO;
import com.api.ecommerce.user.model.User;
import com.api.ecommerce.user.repository.UserRepository;

public class AuthService {
        private UserRepository userRepository;

    @Autowired
    private JwtUtil jwtUtil;

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

    public Optional<User> createUser(UserDTO user) {
    try {
        
        if (user == null || user.getUsername() == null || user.getEmail() == null) {
            throw new IllegalArgumentException("Datos del usuario incompletos");
        }
        User nuevo = new User();
        nuevo.setUsername(user.getUsername());
        nuevo.setEmail(user.getEmail());
        nuevo.setFirstName(user.getFirstName());
        nuevo.setLastName(user.getLastName());
        nuevo.setAvatar(user.getAvatar());
        nuevo.setAddress(user.getAddress());
        nuevo.setCreatedAt(user.getCreatedAt());
        nuevo.setUpdatedAt(user.getUpdatedAt());

        userRepository.save(nuevo);
        return Optional.ofNullable(nuevo); // en caso extremo, podría ser null
    } catch (Exception e) {
        throw new RuntimeException("Error al crear el usuario: " + e.getMessage());
    }
    }
}
