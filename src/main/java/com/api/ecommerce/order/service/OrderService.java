package com.api.ecommerce.order.service;

import com.api.ecommerce.common.dto.PageResponse;
import com.api.ecommerce.common.util.Mapper;
import com.api.ecommerce.order.dto.OrderResponse;
import com.api.ecommerce.order.mapper.OrderMapper;
import com.api.ecommerce.order.model.Order;
import com.api.ecommerce.order.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;
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

    public PageResponse<OrderResponse> getOrdersByUserId(Pageable pageable, String userId) {
        Page<Order> page = orderRepository.findByUserId(mapper.mapStringToObjectId(userId), pageable);
        return orderMapper.toPageResponse(page);
    }
}
