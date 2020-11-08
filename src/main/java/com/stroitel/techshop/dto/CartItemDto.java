package com.stroitel.techshop.dto;

import com.stroitel.techshop.domain.CartItem;
import lombok.Data;

@Data
public class CartItemDto {

    private UserProductDto product;

    private Integer quantity;

    public CartItemDto() {
    }

    public CartItemDto(CartItem cartItem) {

        this.product = new UserProductDto(cartItem.getProduct());
        this.quantity = cartItem.getQuantity();
    }
}
