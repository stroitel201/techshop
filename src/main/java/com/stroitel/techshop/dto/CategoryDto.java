package com.stroitel.techshop.dto;

import com.stroitel.techshop.domain.Category;

import javax.validation.constraints.NotNull;

public class CategoryDto {

    private Long id;

    @NotNull
    private String title;

    public CategoryDto() {
    }

    public CategoryDto(Category category) {

        this.id = category.getId();
        this.title = category.getTitle();
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }
}
