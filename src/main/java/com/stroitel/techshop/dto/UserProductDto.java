package com.stroitel.techshop.dto;

import com.stroitel.techshop.domain.Product;

public class UserProductDto {

    private Long id;

    private String name;

    private String description;

    private String category;

    private String pictureRef;

    private Integer price;

    public UserProductDto() {
    }

    public UserProductDto(Product product) {

        this.id = product.getId();
        this.name = product.getName();
        this.category = product.getCategory().getTitle();
        this.description = product.getDescription();
        this.pictureRef = product.getPictureRef();
        this.price = product.getPrice();
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getCategory() {
        return category;
    }

    public Integer getPrice() {
        return price;
    }

    public String getPictureRef() {
        return pictureRef;
    }
}
