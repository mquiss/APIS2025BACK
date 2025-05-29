package com.api.ecommerce.users.controller;

import com.api.ecommerce.users.service.UserService;
import com.api.ecommerce.users.model.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.api.ecommerce.seguridad.JwtUtil;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

     @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/login")
        public ResponseEntity<?> login(@RequestBody LoginRequest request) {
        try {
            if (request.getUsername() == null || request.getPassword() == null) {
                return ResponseEntity.badRequest().body("Credenciales incompletas");
            }     

            Optional<User> userOpt = userService.findByUsernameAndPassword(request.getUsername(), request.getPassword());

            if (userOpt.isPresent()) {
                String token = jwtUtil.generateToken(request.getUsername());
                Map<String, Object> response = new HashMap<>();
                response.put("token", token);
                response.put("user", userOpt.get());
                System.out.println("Usuario autenticado: " + request.getUsername());
                System.out.println("Token generado: " + token);
                return ResponseEntity.ok(response);
            } else {
                return ResponseEntity.status(401).body("Credenciales inválidas");
            }

        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error interno: " + e.getMessage());
        }
    }
    

    @GetMapping("/")
    public List<User> getUsers() {
        return userService.getAllUsers();
    }


    @GetMapping("/{id}")
    public Optional<User> getUserById(@PathVariable String id) {
        return userService.getUserById(id);
    }

    @PostMapping("/")
    public User createUser(@RequestBody User user) {
        return userService.createUser(user);
    }

    @PutMapping("/{id}")
    public User updateUser(@PathVariable String id, @RequestBody User userDetails) {
        return userService.updateUser(id, userDetails);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable String id) {
        userService.deleteUser(id);
    }
    
}
