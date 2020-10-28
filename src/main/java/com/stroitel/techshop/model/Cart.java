package com.stroitel.techshop.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Map;
import java.util.Set;

@Data
@Entity
@Table(name = "cart")
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Purchase> currentPurchases;

    private Integer sum;

    private void calculateSum(){

        for (Purchase purchase:
             currentPurchases) {
            sum += (purchase.getProduct().getPrice() * purchase.getCount());
        }
    }
}
