package com.api.ecommerce.users.service;

import com.api.ecommerce.users.model.User;
import com.api.ecommerce.users.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.function.Supplier;
import java.util.Map;

import com.api.ecommerce.exepciones.RecursoNoEncontradoException;
import com.api.ecommerce.seguridad.JwtUtil;
import com.api.ecommerce.users.controller.LoginRequest;
import com.api.ecommerce.users.dto.UserDTO;

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

    public Optional<UserDTO> getUserById(String id) {
        try {
            if (id == null || id.isEmpty()) {
                throw new IllegalArgumentException("El ID del usuario no puede ser nulo o vacío");
            }
            if (!userRepository.existsById(id)) {
                throw new RuntimeException("Usuario no encontrado con id: " + id);
            }
            Optional<User> buscado=userRepository.findById(id);
            UserDTO user = new UserDTO();
            user.setId(buscado.get().getId());
            user.setUsername(buscado.get().getUsername());
            user.setEmail(buscado.get().getEmail());   
            user.setFirstName(buscado.get().getFirstName());
            user.setLastName(buscado.get().getLastName());
            user.setAvatar(buscado.get().getAvatar());
            user.setAddress(buscado.get().getAddress());
            user.setCreatedAt(buscado.get().getCreatedAt());
            user.setUpdatedAt(buscado.get().getUpdatedAt());
            return Optional.of(user);
        } catch (Exception e) {
            throw new RecursoNoEncontradoException(); // lanzar excepción personalizada
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

    public Optional<User> updateUser(String id, User userDetails) {
        if (id == null || id.isEmpty()) {
            throw new IllegalArgumentException("El ID del usuario no puede ser nulo o vacío");
        }

        Optional<User> userOptional = userRepository.findById(id);

        if (userOptional.isEmpty()) {
            throw new RecursoNoEncontradoException();
        }

        User user = userOptional.get();

        user.setUsername(userDetails.getUsername());
        user.setEmail(userDetails.getEmail());
        user.setFirstName(userDetails.getFirstName());
        user.setLastName(userDetails.getLastName());
        user.setAvatar(userDetails.getAvatar());
        user.setAddress(userDetails.getAddress());

        User updatedUser = userRepository.save(user);

        return Optional.of(updatedUser);
       
    }

    public void deleteUser(String id) {
        if (id == null || id.isEmpty()) {
            throw new IllegalArgumentException("El ID del usuario no puede ser nulo o vacío");
        }

        Optional<User> userOptional = userRepository.findById(id);

        if (userOptional.isEmpty()) {
            throw new RecursoNoEncontradoException();
        }
        userRepository.deleteById(id);
    }
}