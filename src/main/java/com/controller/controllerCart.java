package com.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.model.CartData;
import com.model.Product;
import com.service.CartService;

import java.util.List;

@RestController
@RequestMapping("/carts")
public class controllerCart {

    @Autowired
    private CartService cartService;

    public ResponseEntity<?> createCart(CartData cartData) {
        // Implementación de la lógica para crear un carrito
        return ResponseEntity.ok("Carrito creado exitosamente");
    }

    public ResponseEntity<?> fetchUserCart(String userId) {
        // Implementación de la lógica para obtener el carrito de un usuario
        return ResponseEntity.ok("Carrito del usuario obtenido exitosamente");
    }

    public ResponseEntity<?> saveUserCart(String userId, List<Product> products) {
        // Implementación de la lógica para guardar el carrito de un usuario
        return ResponseEntity.ok("Carrito del usuario guardado exitosamente");
    }

    @GetMapping
    public ResponseEntity<?> getAllCarts() {
        List<CartData> carts = cartService.getAllCarts();
        return ResponseEntity.ok(carts.isEmpty() ? "No hay carritos disponibles" : carts);
    }

}
