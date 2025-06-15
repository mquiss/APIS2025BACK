package com.api.ecommerce.order.mapper;

import com.api.ecommerce.common.util.Mapper;
import com.api.ecommerce.order.dto.OrderItemResponse;
import com.api.ecommerce.order.dto.OrderResponse;
import com.api.ecommerce.order.model.Order;
import com.api.ecommerce.order.model.OrderItem;
import com.api.ecommerce.product.model.Image;
import com.api.ecommerce.product.model.Product;
import com.api.ecommerce.product.service.ProductService;

import java.time.LocalDateTime;
import java.time.ZoneId;

public class OrderMapperImpl implements OrderMapper {
    private final Mapper mapper;
    private final ProductService productService;

    public OrderMapperImpl(Mapper mapper, ProductService productService) {
        this.productService = productService;
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
        Product product = productService.getProductById(orderItem.getProductId());
        return OrderItemResponse.builder()
                .title(product.getTitle())
                .image(product.getImages().stream().filter(Image::isCover).findFirst().orElseThrow(null).getUrl())
                .quantity(orderItem.getQuantity())
                .unitPrice(orderItem.getUnitPrice())
                .build();
    }
}
