package com.stroitel.techshop.domain;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "productcategory")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", insertable = false, updatable = false, nullable = false)
    private Long id;

    @Column(nullable = false)
    @NotNull
    private String title;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public static class Builder {
        private Long id;
        private String name;

        public Builder() {
        }

        public Builder(Category source) {
            id = source.id;
            name = source.title;

        }

        public Category build() {
            Category distillery = new Category();
            distillery.id = id;
            distillery.title = name;
            return distillery;
        }

        public Builder setId(Long id) {
            this.id = id;
            return this;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
