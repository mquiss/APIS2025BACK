package com.service;

import com.model.CartData;
import com.repository.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import org.bson.types.ObjectId;

@Service
public class CartService {
    @Autowired
    private CartRepository cartRepository;

    public String createCart(CartData cartData) {
        cartRepository.save(cartData);
        return "Carrito creado exitosamente";
    }

    public Optional<CartData> fetchUserCart(String userId) {
        return cartRepository.findById(new ObjectId(userId));
    }

    public List<CartData> getAllCarts() {
        return cartRepository.findAll();
    }
}
