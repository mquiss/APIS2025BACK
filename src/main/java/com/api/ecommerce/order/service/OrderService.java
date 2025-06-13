package com.api.ecommerce.order.service;

import com.api.ecommerce.common.util.Mapper;
import com.api.ecommerce.order.dto.OrderResponse;
import com.api.ecommerce.order.mapper.OrderMapper;
import com.api.ecommerce.order.mapper.OrderMapperImpl;
import com.api.ecommerce.order.repository.OrderRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class OrderService {
    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;
    private final Mapper mapper;

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
        this.mapper = new Mapper();
        this.orderMapper = new OrderMapperImpl(mapper);
    }

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
