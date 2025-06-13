package com.api.ecommerce.order.mapper;

import com.api.ecommerce.common.util.Mapper;
import com.api.ecommerce.order.controller.OrderItemResponse;
import com.api.ecommerce.order.controller.OrderResponse;
import com.api.ecommerce.order.model.Order;
import com.api.ecommerce.order.model.OrderItem;

import java.time.LocalDateTime;
import java.time.ZoneId;

public class OrderMapperImpl implements OrderMapper {
    private final Mapper mapper;

    public OrderMapperImpl(Mapper mapper) {
        this.mapper = mapper;
    }

    @Override
    public OrderResponse toOrderResponse(Order order) {
        return OrderResponse.builder()
                .userId(order.getUserId().toString())
                .products(order.getProducts().stream().map(this::toOrderItemResponse).toList())
                .subtotal(order.getSubtotal())
                .createdAt(LocalDateTime.ofInstant(order.getCreatedAt(), ZoneId.systemDefault()))
                .build();
    }

    @Override
    public OrderItemResponse toOrderItemResponse(OrderItem orderItem) {
        return OrderItemResponse.builder()
                .productId(orderItem.getProductId().toString())
                .quantity(orderItem.getQuantity())
                .unitPrice(orderItem.getUnitPrice())
                .build();
    }
}
