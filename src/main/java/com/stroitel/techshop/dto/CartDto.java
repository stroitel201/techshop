package com.stroitel.techshop.dto;

import com.stroitel.techshop.domain.Cart;

import java.util.List;
import java.util.stream.Collectors;

public class CartDto {

    List<CartItemDto> list;

    private boolean deliveryIncluded;

    private double itemsCost;

    public CartDto(Cart cart){
        this.list = cart.getCartItems().stream()
                .map(cartItem -> new CartItemDto(cartItem))
                .collect(Collectors.toList());

        this.deliveryIncluded = cart.isDeliveryIncluded();
        this.itemsCost = cart.getItemsCost();
    }
}
