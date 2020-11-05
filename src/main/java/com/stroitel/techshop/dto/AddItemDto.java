package com.stroitel.techshop.dto;

import javax.validation.constraints.NotNull;

public class AddItemDto {

    @NotNull
    private Long id;

    @NotNull
    private Integer quantity;

    public Long getId() {
        return id;
    }

    public Integer getQuantity() {
        return quantity;
    }
}
