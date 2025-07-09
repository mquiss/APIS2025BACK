package com.api.ecommerce.cart.service;

import com.api.ecommerce.cart.dto.CartItemRequest;
import com.api.ecommerce.cart.dto.CartRequest;
import com.api.ecommerce.cart.dto.CartResponse;
import com.api.ecommerce.cart.mapper.CartMapper;
import com.api.ecommerce.cart.model.Cart;
import com.api.ecommerce.cart.model.CartItem;
import com.api.ecommerce.cart.repository.CartRepository;
import com.api.ecommerce.common.exception.CartNotFoundException;
import com.api.ecommerce.common.util.Mapper;
import com.api.ecommerce.user.service.UserService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Size;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

import org.bson.types.ObjectId;

import static java.util.stream.Collectors.toList;

@Service
@RequiredArgsConstructor
public class CartService {
    private final CartRepository cartRepository;
    private final UserService userService;
    private final CartMapper cartMapper;
    private final Mapper mapper;

    public CartResponse createCart(CartRequest cartRequest) {
        userService.findUserById(mapper.mapStringToObjectId(cartRequest.getUserId())); // exception si no hay user
        Cart cart = cartMapper.toCart(cartRequest); // en mapper tira exception si no hay product
        cartRepository.save(cart);
        return cartMapper.toCartResponse(cart);
    }

    public CartResponse getCartByUserId(String userId) {
        ObjectId objectUserId = mapper.mapStringToObjectId(userId);
        userService.findUserById(objectUserId); // exception si no hay user
        return cartMapper.toCartResponse(cartRepository.findByUserId(objectUserId).orElseThrow(CartNotFoundException::new));
    }

    public List<CartResponse> getAllCarts() {
        return cartRepository
                .findAll()
                .stream()
                .map(cartMapper::toCartResponse)
                .toList();
    }

    /**
     *  Recibe una lista de productos y la compara con la que tiene en la base de datos(carrito de compras), si el producto
     *  se encuentra en ambas listas suma las cantidades y si no, agrega el nuevo producto con la cantidad indicada
     *  en el request
     * **/
    public CartResponse updateProducts(String userId, List<CartItemRequest> productsRequest) {
        ObjectId objectUserId = mapper.mapStringToObjectId(userId);
        userService.findUserById(objectUserId);

        Cart cart = cartRepository.findByUserId(objectUserId)
                .orElseThrow(CartNotFoundException::new);

        List<CartItem> newItems = (productsRequest == null || productsRequest.isEmpty())
                ? Collections.emptyList()
                : productsRequest.stream()
                .map(cartMapper::toCartItem)
                .toList();

        List<CartItem> existingItems = cart.getProducts() == null
                ? new ArrayList<>()
                : new ArrayList<>(cart.getProducts());

        Map<ObjectId, Integer> quantityMap = new HashMap<>();

        for (CartItem item : existingItems) {
            quantityMap.put(item.getProductId(), item.getQuantity());
        }

        for (CartItem item : newItems) {
            quantityMap.merge(item.getProductId(), item.getQuantity(), Integer::sum);
        }

        List<CartItem> mergedItems = quantityMap.entrySet().stream()
                .map(entry -> {
                    CartItemRequest req = new CartItemRequest();
                    req.setProductId(entry.getKey().toString());
                    req.setQuantity(entry.getValue());
                    return cartMapper.toCartItem(req);
                })
                .toList();

        cart.setProducts(mergedItems);
        cartRepository.save(cart);

        return cartMapper.toCartResponse(cart);
    }

    public CartResponse replaceProducts(String userId, List<CartItemRequest> productsRequest) {
        ObjectId objectUserId = mapper.mapStringToObjectId(userId);
        userService.findUserById(objectUserId);

        Cart cart = cartRepository.findByUserId(objectUserId)
                .orElseThrow(CartNotFoundException::new);

        List<CartItem> items = (productsRequest == null || productsRequest.isEmpty())
                ? Collections.emptyList()
                : productsRequest.stream()
                .map(cartMapper::toCartItem)
                .toList();

        cart.setProducts(items);
        cartRepository.save(cart);

        return cartMapper.toCartResponse(cart);
    }
}