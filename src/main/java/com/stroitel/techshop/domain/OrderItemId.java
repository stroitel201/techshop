package com.stroitel.techshop.domain;

import lombok.Data;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Data
@Embeddable
public class OrderItemId implements Serializable {

    private long orderId;
    private long productId;

    public OrderItemId() {
    }

    public OrderItemId(long orderId, long productId) {
        this.orderId = orderId;
        this.productId = productId;
    }
}
