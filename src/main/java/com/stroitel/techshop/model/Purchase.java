package com.stroitel.techshop.model;

import lombok.Data;

import javax.persistence.*;
import java.sql.Date;

@Data
@Entity
@Table(name = "purchase")
public class Purchase {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToOne
    private Product product;

    private Date date;

    private Integer count;

    public Product getProduct() {
        return product;
    }

    public Integer getCount() {
        return count;
    }

    private Boolean isComitted = false;
}
