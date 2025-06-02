package com.api.ecommerce.users.service;

import com.api.ecommerce.users.model.User;
import com.api.ecommerce.users.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.Map;

import com.api.ecommerce.seguridad.JwtUtil;
import com.api.ecommerce.users.controller.LoginRequest;

@Service
public class UserService {

    @Autowired
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

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public Optional<User> getUserById(String id) {
        return userRepository.findById(id);
    }

public Optional<User> createUser(User user) {
    try {
        User nuevo = userRepository.save(user);
        return Optional.ofNullable(nuevo); // en caso extremo, podría ser null
    } catch (Exception e) {
        return Optional.empty(); // error al guardar
    }
}

    public User updateUser(String id, User userDetails) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
        user.setUsername(userDetails.getUsername());
        user.setEmail(userDetails.getEmail());
        user.setFirstName(userDetails.getFirstName());
        user.setLastName(userDetails.getLastName());
        user.setAvatar(userDetails.getAvatar());
        user.setAddress(userDetails.getAddress());
        return userRepository.save(user);
    }

    public void deleteUser(String id) {
        userRepository.deleteById(id);
    }
}