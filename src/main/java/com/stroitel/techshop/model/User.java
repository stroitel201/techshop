package com.stroitel.techshop.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String password;

    @OneToOne(cascade = CascadeType.ALL)
    private Customer customer;
}
