package com.api.ecommerce.cart.controller;

import com.api.ecommerce.cart.model.Cart;
import com.api.ecommerce.cart.model.CartItem;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.api.ecommerce.cart.service.CartService;

import java.util.List;


// TODO: prioridad
// - export const createCart = (cartData) => api.post(`/carts`, cartData);
// - export const fetchUserCart = (userId) => api.get(`/carts/${userId}`);
// - export const saveUserCart = (userId, products) => api.put(`/carts/${userId}`, { products });

// TODO: implementar excepciones, dto para response(donde se resuelve ObjectId -> String) y request(para crear nuevo carrito)
@RestController
@RequiredArgsConstructor
@RequestMapping("/carts")
public class CartController {
    private final CartService cartService;

    @PostMapping("/create")
    public ResponseEntity<?> createCart(@RequestBody Cart Cart) { // esta cosa se ejecuta despues de register
        return ResponseEntity.ok("Carrito creado exitosamente");
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<?> fetchUserCart(@PathVariable String userId) {
        return ResponseEntity.ok("Carrito del usuario obtenido exitosamente");
    }

    // TODO: definir si la lista de productos viene con productos o productItem, dónde se haría la conversión si es necesaria
    @PutMapping("/save/{userId}")
    public ResponseEntity<?> saveUserCart(@PathVariable String userId, List<CartItem> products) {
        return ResponseEntity.ok("Carrito del usuario guardado exitosamente");
    }

    @GetMapping("/")
    public ResponseEntity<?> getAllCarts() {
        List<Cart> carts = cartService.getAllCarts();
        return ResponseEntity.ok(carts.isEmpty() ? "No hay carritos disponibles" : carts);
    }
}
