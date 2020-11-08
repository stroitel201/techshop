package com.stroitel.techshop.domain;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@Table(name = "order_item")
public class OrderItem implements Serializable {

    @EmbeddedId
    private OrderItemId pk;

    @MapsId("orderId")
    @JoinColumn(name = "order_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Order order;

    @MapsId("productId")
    @JoinColumn(name = "product_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Product product;

    @Column(name = "quantity")
    private int quantity;

    public OrderItem(){}
    public OrderItem(Order order, Product product, int quantity){

        this.pk = new OrderItemId(order.getId(), product.getId());
        this.order = order;
        this.product = product;
        this.quantity = quantity;
    }

    public double calculateCost() {
        return quantity * product.getPrice();
    }
}
