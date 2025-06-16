package com.api.ecommerce.order.mapper;

import com.api.ecommerce.common.dto.PageResponse;
import com.api.ecommerce.order.dto.OrderItemResponse;
import com.api.ecommerce.order.dto.OrderResponse;
import com.api.ecommerce.order.model.Order;
import com.api.ecommerce.order.model.OrderItem;
import org.springframework.data.domain.Page;

public interface OrderMapper {
    OrderResponse toOrderResponse(Order order);
    OrderItemResponse toOrderItemResponse(OrderItem orderItem);
    PageResponse<OrderResponse> toPageResponse(Page<Order> page);
}