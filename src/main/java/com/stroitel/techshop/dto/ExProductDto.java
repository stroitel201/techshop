package com.stroitel.techshop.dto;

import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Data
public class ExProductDto {

    @NotNull
    private String name;

    @NotNull
    private String description;

    @NotNull
    private String category;

    @NotNull
    @Min(0)
    private Integer price;

}
