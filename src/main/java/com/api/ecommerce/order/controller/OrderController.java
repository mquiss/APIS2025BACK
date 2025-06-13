package com.api.ecommerce.order.controller;

import com.api.ecommerce.order.service.OrderService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class OrderController {
    // TODO: prioridad
    // export const fetchAllOrders = () => api.get('/orders');
    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    public ResponseEntity<List<OrderResponse>> getAllOrders() {
        List<OrderResponse> orders = orderService.getOrders();
        return orders.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(orders);
    }

    // TODO: opcional
    // obtener compras por userId(mejor que el de arriba)
    public ResponseEntity<List<OrderResponse>> getOrdersByUserId(String userId) {
        List<OrderResponse> orders = orderService.getOrdersByUserId(userId);
        return orders.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(orders);
    }
}
