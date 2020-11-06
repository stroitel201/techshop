package com.stroitel.techshop.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class ExAddItemDto {

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
