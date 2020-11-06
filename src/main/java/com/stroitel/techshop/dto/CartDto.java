package com.stroitel.techshop.dto;

import com.stroitel.techshop.domain.Cart;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

@Data
public class CartDto {

    List<CartItemDto> list;

    private boolean deliveryIncluded;

    private double itemsCost;

    public CartDto() {
    }

    public CartDto(Cart cart){
        this.list = cart.getCartItems().stream()
                .map(CartItemDto::new)
                .collect(Collectors.toList());

        this.deliveryIncluded = cart.isDeliveryIncluded();
        this.itemsCost = cart.getItemsCost();
    }

    public List<CartItemDto> getList() {
        return list;
    }

    public boolean isDeliveryIncluded() {
        return deliveryIncluded;
    }

    public double getItemsCost() {
        return itemsCost;
    }
}
