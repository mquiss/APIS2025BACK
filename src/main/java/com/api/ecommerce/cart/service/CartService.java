package com.api.ecommerce.cart.service;

import com.api.ecommerce.cart.dto.CartRequest;
import com.api.ecommerce.cart.dto.CartResponse;
import com.api.ecommerce.cart.dto.ProductsRequest;
import com.api.ecommerce.cart.mapper.CartMapper;
import com.api.ecommerce.cart.model.Cart;
import com.api.ecommerce.cart.model.CartItem;
import com.api.ecommerce.cart.repository.CartRepository;
import com.api.ecommerce.common.exception.CartNotFoundException;
import com.api.ecommerce.common.util.Mapper;
import com.api.ecommerce.user.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import org.bson.types.ObjectId;

@Service
@RequiredArgsConstructor
public class CartService {
    private final CartRepository cartRepository;
    private final UserService userService;
    private final CartMapper cartMapper;
    private final Mapper mapper;

    public CartResponse createCart(CartRequest cartRequest) {
        userService.findUserById(mapper.mapStringToObjectId(cartRequest.getUserId())); // exception si no hay user
        Cart cart = cartMapper.toCart(cartRequest); // en mapper tira exception si no hay product
        cartRepository.save(cart);
        return cartMapper.toCartResponse(cart);
    }

    public CartResponse getCartByUserId(String userId) {
        ObjectId objectUserId = mapper.mapStringToObjectId(userId);
        userService.findUserById(objectUserId); // exception si no hay user
        Cart cart = cartRepository.findByUserId(objectUserId);
        if (cart == null) {
            throw new CartNotFoundException("Cart not found for user ID: " + userId);
        }
        return cartMapper.toCartResponse(cart);
    }

    public List<CartResponse> getAllCarts() {
        return cartRepository
                .findAll()
                .stream()
                .map(cartMapper::toCartResponse)
                .toList();
    }

    public CartResponse updateProducts(String userId, ProductsRequest productsRequest) {
        List<CartItem> products = Collections.emptyList();

        if (productsRequest.getProducts() != null && !productsRequest.getProducts().isEmpty()) {
            products = productsRequest.getProducts().stream()
                    .map(cartMapper::toCartItem)
                    .toList();
        }

        ObjectId objectUserId = mapper.mapStringToObjectId(userId);
        userService.findUserById(objectUserId);

        Cart cart = cartRepository.findByUserId(objectUserId);

        cart.setProducts(products);
        cartRepository.save(cart);

        return cartMapper.toCartResponse(cart);
    }
}