package com.api.ecommerce.order.controller;

import com.api.ecommerce.common.dto.PageResponse;
import com.api.ecommerce.order.dto.OrderResponse;
import com.api.ecommerce.order.service.OrderService;
import jakarta.validation.constraints.Size;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/orders")
public class OrderController {
    private final OrderService orderService;

    @GetMapping
    public ResponseEntity<List<OrderResponse>> getAllOrders() {
        List<OrderResponse> orders = orderService.getOrders();
        return ResponseEntity.ok(orders);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<OrderResponse>> getOrdersByUserId(@PathVariable String userId) {
        List<OrderResponse> orders = orderService.getOrdersByUserId(userId);
        return ResponseEntity.ok(orders);
    }

    @GetMapping("/user/{userId}/page")
    public ResponseEntity<PageResponse<OrderResponse>> getOrdersByUserId(@PageableDefault(size = 5, sort = "title") Pageable pageable, @Size(min = 24, max = 24, message = "id must be 24 characters long") @PathVariable String userId) {
        PageResponse<OrderResponse> orders = orderService.getOrdersByUserId(pageable, userId);
        return ResponseEntity.ok(orders);
    }

}
