package com.stroitel.techshop.domain;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.ArrayList;

import java.util.List;
import java.util.stream.Collectors;

import static javax.persistence.TemporalType.TIMESTAMP;


@Data
@Entity
@Table(name = "customer_order")
public class Order implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", insertable = false, updatable = false, nullable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_account_id")
    private UserAccount userAccount;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true,
            targetEntity = OrderItem.class, mappedBy = "order")
    private List<OrderItem> orderItems = new ArrayList<>(0);

    @Column(name = "is_executed", nullable = false)
    private boolean isExecuted = false;

    @Column(name = "date_created", nullable = false)
    @Temporal(TIMESTAMP)
    private Date dateCreated;

    @Column(name = "delivery_cost")
    private int deliveryCost;

    @Column(name = "delivery_included", nullable = false)
    private boolean deliveryIncluded;

    @Column(name = "cost")
    private double totalPrice;

    public Order() {
    }

    public Order(UserAccount userAccount){
        this.userAccount = userAccount;
        totalPrice = 0;
        deliveryIncluded = userAccount.getCart().isDeliveryIncluded();
        deliveryCost = deliveryIncluded ? 100 : 0;
        dateCreated = new Date(System.currentTimeMillis());
    }

    public void calculateTotalPrice(){

        userAccount.getCart().getCartItems()
                .stream()
                .map(cartItem -> new OrderItem(this, cartItem.getProduct(), cartItem.getQuantity()))
                .forEach(orderItems::add);

        for (OrderItem item:
             orderItems) {
            totalPrice += item.calculateCost();
        }
    }
}
