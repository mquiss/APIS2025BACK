package com.api.ecommerce.cart.controller;

import com.api.ecommerce.cart.model.Cart;
import com.api.ecommerce.cart.model.CartItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.ecommerce.cart.service.CartService;

import java.util.List;

// TODO: implementar excepciones, dto para response(donde se resuelve ObjectId -> String) y request
@RestController
@RequestMapping("/carts")
public class CartController {

    @Autowired
    private CartService cartService;

    public ResponseEntity<?> createCart(Cart Cart) {
        return ResponseEntity.ok("Carrito creado exitosamente");
    }

    public ResponseEntity<?> fetchUserCart(String userId) {
        return ResponseEntity.ok("Carrito del usuario obtenido exitosamente");
    }

    // TODO: definir si la lista de productos viene con productos o productItem, dónde se haría la conversión si es necesaria
    public ResponseEntity<?> saveUserCart(String userId, List<CartItem> products) {
        return ResponseEntity.ok("Carrito del usuario guardado exitosamente");
    }

    @GetMapping("/")
    public ResponseEntity<?> getAllCarts() {
        List<Cart> carts = cartService.getAllCarts();
        return ResponseEntity.ok(carts.isEmpty() ? "No hay carritos disponibles" : carts);
    }
}
