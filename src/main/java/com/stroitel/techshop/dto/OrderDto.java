package com.stroitel.techshop.dto;

import com.stroitel.techshop.domain.Order;
import lombok.Data;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Data
public class OrderDto {

    private Long orderId;

    private Long userId;

    List<OrderItemDto> list;

    private boolean isDeliveryIncluded;

    private boolean isExecuted;

    private double deliveryCost;

    private Date dateCreated;

    private double totalPrice;

    public OrderDto() {
    }

    public OrderDto(Order order){

        this.orderId = order.getId();
        this.userId = order.getUserAccount().getId();
        this.list = order.getOrderItems()
                .stream()
                .map(OrderItemDto::new)
                .collect(Collectors.toList());

        this.isExecuted = order.isExecuted();
        this.totalPrice = order.getTotalPrice();
        this.isDeliveryIncluded = order.isDeliveryIncluded();
        this.dateCreated = order.getDateCreated();
        this.deliveryCost = order.getDeliveryCost();
    }
}
