package com.stroitel.techshop.dto;

import com.stroitel.techshop.domain.Product;

public class AdminProductDto {

    private Long id;

    private String name;

    private String description;

    private String category;

    private String pictureRef;

    private Integer price;

    private boolean isAvailable;

    public AdminProductDto() {
    }



    public AdminProductDto(Product product) {

        this.id = product.getId();
        this.name = product.getName();
        this.category = product.getCategory().getTitle();
        this.description = product.getDescription();
        this.pictureRef = product.getPictureRef();
        this.price = product.getPrice();
        this.isAvailable = product.isAvailable();
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

    public boolean isAvailable() {
        return isAvailable;
    }

    public String getPictureRef() {
        return pictureRef;
    }
}
