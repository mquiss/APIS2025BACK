package com.api.ecommerce.order.service;

import com.api.ecommerce.common.util.Mapper;
import com.api.ecommerce.order.dto.OrderResponse;
import com.api.ecommerce.order.mapper.OrderMapper;
import com.api.ecommerce.order.mapper.OrderMapperImpl;
import com.api.ecommerce.order.repository.OrderRepository;
import com.api.ecommerce.product.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;
    private final ProductService productService;
    private final Mapper mapper;

    public List<OrderResponse> getOrders() {
        return orderRepository
                .findAll()
                .stream()
                .map(orderMapper::toOrderResponse)
                .toList();
    }

    public List<OrderResponse> getOrdersByUserId(String userId) {
        return orderRepository
                .findByUserId(mapper.mapStringToObjectId(userId))
                .stream()
                .map(orderMapper::toOrderResponse)
                .toList();
    }
}
