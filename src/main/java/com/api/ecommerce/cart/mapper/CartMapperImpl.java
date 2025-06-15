package com.api.ecommerce.cart.mapper;

import com.api.ecommerce.cart.dto.CartItemRequest;
import com.api.ecommerce.cart.dto.CartItemResponse;
import com.api.ecommerce.cart.dto.CartRequest;
import com.api.ecommerce.cart.dto.CartResponse;
import com.api.ecommerce.cart.model.Cart;
import com.api.ecommerce.cart.model.CartItem;
import com.api.ecommerce.common.util.Mapper;
import com.api.ecommerce.product.model.Image;
import com.api.ecommerce.product.model.Product;
import com.api.ecommerce.product.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Component
@RequiredArgsConstructor
public class CartMapperImpl implements CartMapper {
    private final Mapper mapper;
    private final ProductService productService;

    @Override
    public CartResponse toCartResponse(Cart cart) {
        List<CartItemResponse> products = Collections.emptyList();

        if (cart.getProducts() != null && !cart.getProducts().isEmpty()) {
            products = cart.getProducts().stream()
                    .map(this::toCartItemResponse)
                    .toList();
        }

        return CartResponse.builder()
                .userId(cart.getUserId().toString())
                .products(products)
                .build();
    }

    @Override
    public CartItemResponse toCartItemResponse(CartItem cartItem) {
        Product product = productService.getProductById(cartItem.getProductId());

        return CartItemResponse.builder()
                .productId(product.getId().toString())
                .quantity(cartItem.getQuantity())
                .title(product.getTitle())
                .price(product.getPrice())
                .discountPercentage(product.getDiscountPercentage())
                .image(product.getImages()
                        .stream()
                        .filter(Image::isCover)
                        .findFirst()
                        .map(Image::getUrl)
                        .orElse(null))
                .stock(product.getStock())
                .build();
    }

    @Override
    public Cart toCart(CartRequest cartRequest) {
        List<CartItem> products = Collections.emptyList();

        if (cartRequest.getProducts() != null && !cartRequest.getProducts().isEmpty()) {
            products = cartRequest.getProducts().stream()
                    .map(this::toCartItem)
                    .toList();
        }

        return Cart.builder()
                .userId(mapper.mapStringToObjectId(cartRequest.getUserId()))
                .products(products)
                .build();
    }

    @Override
    public CartItem toCartItem(CartItemRequest cartItemRequest) {
        return CartItem.builder()
                .productId(mapper.mapStringToObjectId(cartItemRequest.getProductId()))
                .quantity(cartItemRequest.getQuantity())
                .build();
    }
}
