package com.api.ecommerce.user.service;

import com.api.ecommerce.auth.dto.RegisterRequest;
import com.api.ecommerce.common.util.Mapper;
import com.api.ecommerce.user.dto.*;
import com.api.ecommerce.user.mapper.UserMapper;
import com.api.ecommerce.user.model.Address;
import com.api.ecommerce.user.model.User;
import com.api.ecommerce.user.repository.UserRepository;

import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


import com.api.ecommerce.common.exception.RecursoNoEncontradoException;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserMapper userMapper;
    private final Mapper mapper;

    // FIXME: posible exception si userRepository no cuenta con usuarios e intenta hacer stream
    public List<UserResponse> getAllUsers() {
        return userRepository.findAll()
                .stream()
                .map(userMapper::toUserResponse)
                .toList();
    }

    public UserResponse getUserById(String userId) {
        User user = userRepository.findById(mapper.mapStringToObjectId(userId)).orElseThrow(RecursoNoEncontradoException::new);
        return userMapper.toUserResponse(user);
    }

    public UserResponse updateUsername(UsernameRequest usernameRequest, String id) {
        User user = userRepository.findById(mapper.mapStringToObjectId(id)).orElseThrow(RecursoNoEncontradoException::new);
        user.setUsername(usernameRequest.username());
        userRepository.save(user);
        return userMapper.toUserResponse(user);
    }

    public UserResponse updateFirstName(FirstNameRequest firstNameRequest, String id) {
        User user = userRepository.findById(mapper.mapStringToObjectId(id)).orElseThrow(RecursoNoEncontradoException::new);
        user.setFirstName(firstNameRequest.firstName());
        userRepository.save(user);
        return userMapper.toUserResponse(user);
    }

    public UserResponse updateLastName(LastNameRequest lastNameRequest, String id) {
        User user = userRepository.findById(mapper.mapStringToObjectId(id)).orElseThrow(RecursoNoEncontradoException::new);
        user.setLastName(lastNameRequest.lastName());
        userRepository.save(user);
        return userMapper.toUserResponse(user);
    }

    public UserResponse updateAddress(AddressRequest addressRequest, String id) {
        User user = userRepository.findById(mapper.mapStringToObjectId(id)).orElseThrow(RecursoNoEncontradoException::new);
        Address address = user.getAddress();
        address.setStreet(addressRequest.street());
        address.setState(addressRequest.state());
        address.setCountry(addressRequest.country());
        user.setAddress(address);
        userRepository.save(user);
        return userMapper.toUserResponse(user);
    }

    public UserResponse updateEmail(EmailRequest emailRequest, String id) {
        User user = userRepository.findById(mapper.mapStringToObjectId(id)).orElseThrow(RecursoNoEncontradoException::new);
        user.setEmail(emailRequest.email());
        userRepository.save(user);
        return userMapper.toUserResponse(user);
    }

    public UserResponse updateAvatar(AvatarRequest avatarRequest, String id) {
        User user = userRepository.findById(mapper.mapStringToObjectId(id)).orElseThrow(RecursoNoEncontradoException::new);
        user.setAvatar(avatarRequest.avatar());
        userRepository.save(user);
        return userMapper.toUserResponse(user);
    }



    public UserResponse updatePassword(PasswordRequest request, String id) {
        User user = userRepository.findById(null == id || id.isEmpty() ? null : new ObjectId(id))
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        System.out.println("ID del usuario: " + id);
        System.out.println(userMapper.toUserResponse(user));
        String hashedPassword = passwordEncoder.encode(request.getNewPassword());
        user.setPassword(hashedPassword);
        User saved = userRepository.save(user);

        UserResponse userResponse = userMapper.toUserResponse(saved);
        return userResponse; // si usás un mapper
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

    public User findUserById(ObjectId id) {
        return userRepository.findById(id).orElseThrow(RecursoNoEncontradoException::new);
    }

    public UserResponse createUser(RegisterRequest registerRequest) {
        User user = userMapper.toUser(registerRequest);
        userRepository.save(user);
        return userMapper.toUserResponse(user);
    }
}