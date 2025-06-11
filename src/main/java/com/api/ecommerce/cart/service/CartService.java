package com.api.ecommerce.cart.service;

import com.api.ecommerce.cart.model.Cart;
import com.api.ecommerce.cart.repository.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import org.bson.types.ObjectId;

@Service
public class CartService {
    @Autowired
    private CartRepository cartRepository;

    public String createCart(Cart cart) {
        cartRepository.save(cart);
        return "Carrito creado exitosamente";
    }

    public Optional<Cart> fetchUserCart(String userId) {
        return cartRepository.findById(new ObjectId(userId));
    }

    public List<Cart> getAllCarts() {
        return cartRepository.findAll();
    }
}
