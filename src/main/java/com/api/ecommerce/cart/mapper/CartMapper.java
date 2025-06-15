package com.api.ecommerce.cart.mapper;

import com.api.ecommerce.cart.dto.CartItemRequest;
import com.api.ecommerce.cart.dto.CartItemResponse;
import com.api.ecommerce.cart.dto.CartRequest;
import com.api.ecommerce.cart.dto.CartResponse;
import com.api.ecommerce.cart.model.Cart;
import com.api.ecommerce.cart.model.CartItem;

public interface CartMapper {
    CartResponse toCartResponse(Cart cart);
    CartItemResponse toCartItemResponse(CartItem cart);
    Cart toCart(CartRequest cartRequest);
    CartItem toCartItem(CartItemRequest cartItemRequest);
}
