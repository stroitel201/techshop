package com.stroitel.techshop.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Set;

@Data
@Entity
@Table(name = "customer")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String surname;

    private String email;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Purchase> historyOfPurchases;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Cart cart;
}
