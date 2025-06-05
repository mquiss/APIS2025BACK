package com.api.ecommerce.users.controller;

import com.api.ecommerce.users.service.UserService;
import com.api.ecommerce.exepciones.ErrorCreacionException;
import com.api.ecommerce.exepciones.RecursoNoEncontradoException;
import com.api.ecommerce.users.dto.UserDTO;
import com.api.ecommerce.users.model.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {
        try {
            return userService.login(request);
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error interno: " + e.getMessage());
        }
    }

    @GetMapping("/")
    public List<User> getUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getUserById(@PathVariable String id) {
        try{
            Optional<UserDTO> user = userService.getUserById(id);
            if (user.isPresent()) {
                
                return ResponseEntity.ok(user.get());
            }
                else {
            return ResponseEntity.status(404).body("Usuario no encontrado");
        }
        }catch(Exception e){
            return ResponseEntity.status(500).body("Error: " + e.getMessage());
        }
    }
    @PostMapping("/nuevo")
    public ResponseEntity<?> createUser(@RequestBody UserDTO user) {
        try {
            return userService.createUser(user) != null 
                ? ResponseEntity.status(201).body(user) 
                : ResponseEntity.status(400).body("Error al crear el usuario");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error interno: " + e.getMessage());
        }

    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateUser(@PathVariable String id, @RequestBody User userDetails) {
       try{

           return userService.updateUser(id, userDetails)!=null
               ? ResponseEntity.ok(userDetails)
               : ResponseEntity.status(404).body("Usuario no encontrado");

       }catch (Exception e) {
           return ResponseEntity.status(500).body("Error: " + e.getMessage());
       }
       
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable String id) {
        userService.deleteUser(id);
    }
}
