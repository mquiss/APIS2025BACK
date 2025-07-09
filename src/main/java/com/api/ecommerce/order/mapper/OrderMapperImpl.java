package com.api.ecommerce.order.mapper;

import com.api.ecommerce.common.dto.PageResponse;
import com.api.ecommerce.order.dto.OrderItemResponse;
import com.api.ecommerce.order.dto.OrderResponse;
import com.api.ecommerce.order.model.Order;
import com.api.ecommerce.order.model.OrderItem;
import com.api.ecommerce.product.model.Image;
import com.api.ecommerce.product.model.Product;
import com.api.ecommerce.product.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;

@Component
@RequiredArgsConstructor
public class OrderMapperImpl implements OrderMapper {
    private final ProductService productService;

    @Override
    public OrderResponse toOrderResponse(Order order) {
        return OrderResponse.builder()
                .id(order.getId().toString())
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
                .image(product.getImages().stream().filter(Image::isCover).findFirst().orElseThrow(RuntimeException::new).getUrl())
                .quantity(orderItem.getQuantity())
                .unitPrice(orderItem.getUnitPrice())
                .build();
    }

    @Override
    public PageResponse<OrderResponse> toPageResponse(Page<Order> page) {
        List<OrderResponse> content = page.getContent()
                .stream()
                .map(this::toOrderResponse)
                .toList();

        return new PageResponse<>(
                content,
                page.getNumber(),
                page.getSize(),
                page.getTotalElements(),
                page.getTotalPages(),
                page.isLast(),
                page.isFirst()
        );
    }
}
