package com.api.ecommerce.user.service;

import com.api.ecommerce.user.model.User;
import com.api.ecommerce.user.repository.UserRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;


import com.api.ecommerce.common.exception.RecursoNoEncontradoException;

import com.api.ecommerce.user.dto.UserDTO;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;


    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public Optional<UserDTO> getUserById(String id) {
        try {
            if (id == null || id.isEmpty()) {
                throw new IllegalArgumentException("El ID del usuario no puede ser nulo o vacío");
            }
            if (!userRepository.existsById(new ObjectId(id))) {
                throw new RuntimeException("Usuario no encontrado con id: " + id);
            }
            Optional<User> buscado=userRepository.findById(new ObjectId(id));
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


    public Optional<User> updateUser(String id, User userDetails) {
        if (id == null || id.isEmpty()) {
            throw new IllegalArgumentException("El ID del usuario no puede ser nulo o vacío");
        }

        Optional<User> userOptional = userRepository.findById(new ObjectId(id));

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

        Optional<User> userOptional = userRepository.findById(new ObjectId(id));

        if (userOptional.isEmpty()) {
            throw new RecursoNoEncontradoException();
        }
        userRepository.deleteById(new ObjectId(id));
    }
}