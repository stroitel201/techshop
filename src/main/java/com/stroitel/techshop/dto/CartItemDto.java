package com.stroitel.techshop.dto;

import com.stroitel.techshop.domain.CartItem;

public class CartItemDto {

    private UserProductDto userProductDto;

    private int quantity;

    public CartItemDto(CartItem cartItem) {

        this.userProductDto = new UserProductDto(cartItem.getProduct());
        this.quantity = cartItem.getQuantity();
    }
}
