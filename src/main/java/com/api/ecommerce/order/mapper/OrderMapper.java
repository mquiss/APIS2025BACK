package com.api.ecommerce.order.mapper;

import com.api.ecommerce.order.dto.OrderItemResponse;
import com.api.ecommerce.order.dto.OrderResponse;
import com.api.ecommerce.order.model.Order;
import com.api.ecommerce.order.model.OrderItem;

public interface OrderMapper {
    OrderResponse toOrderResponse(Order order);

    OrderItemResponse toOrderItemResponse(OrderItem orderItem);
}
