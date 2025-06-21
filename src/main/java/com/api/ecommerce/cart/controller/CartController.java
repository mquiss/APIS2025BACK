package com.api.ecommerce.cart.controller;

import com.api.ecommerce.cart.dto.CartItemRequest;
import com.api.ecommerce.cart.dto.CartRequest;
import com.api.ecommerce.cart.dto.CartResponse;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Size;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.api.ecommerce.cart.service.CartService;

import java.util.List;

@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/carts")
public class CartController {
    private final CartService cartService;

    @GetMapping
    public ResponseEntity<List<CartResponse>> getAllCarts() {
        return ResponseEntity.ok(cartService.getAllCarts());
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<CartResponse> getCartByUserId(@Size(min = 24, max = 24, message = "id must be 24 characters long") @PathVariable String userId) {
        return ResponseEntity.ok(cartService.getCartByUserId(userId));
    }

    @PostMapping
    public ResponseEntity<CartResponse> createCart(@Valid @RequestBody CartRequest cartRequest) {
        return ResponseEntity.ok(cartService.createCart(cartRequest));
    }

    @PatchMapping("/{userId}/products")
    public ResponseEntity<CartResponse> updateProducts(@Size(min = 24, max = 24, message = "id must be 24 characters long") @PathVariable String userId, @Valid @RequestBody List<CartItemRequest> productsRequest) {
        return ResponseEntity.ok(cartService.updateProducts(userId, productsRequest));
    }
}