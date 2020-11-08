package com.stroitel.techshop.dto;

import com.stroitel.techshop.domain.OrderItem;
import lombok.Data;

@Data
public class OrderItemDto {

    private UserProductDto product;

    private Integer quantity;

    public OrderItemDto(OrderItem orderItem) {

        this.product = new UserProductDto(orderItem.getProduct());
        this.quantity = orderItem.getQuantity();
    }
}
