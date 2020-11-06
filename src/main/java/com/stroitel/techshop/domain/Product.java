package com.stroitel.techshop.domain;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Data
@Entity
@Table(name = "product")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", insertable = false, updatable = false, nullable = false)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;

    @Column(nullable = false)
    @NotNull
    private String name;

    @Column(nullable = false)
    @NotNull
    private String description;

    @Column(nullable = false)
    @NotNull
    @Min(0)
    private Integer price;

    private String pictureRef;

    @Column(name = "available", nullable = false)
    private boolean available = true;

    public Product(){}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public @NotNull  Integer getPrice() {
        return price;
    }

    public void setPrice(@NotNull Integer price) {
        this.price = price;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Product product = (Product) o;

        if (available != product.available) return false;
        if (id != null ? !id.equals(product.id) : product.id != null) return false;
        if (category != null ? !category.equals(product.category) : product.category != null) return false;
        if (name != null ? !name.equals(product.name) : product.name != null) return false;
        if (description != null ? !description.equals(product.description) : product.description != null) return false;
        return price != null ? price.equals(product.price) : product.price == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (category != null ? category.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (price != null ? price.hashCode() : 0);
        result = 31 * result + (available ? 1 : 0);
        return result;
    }

    public static class Builder {
        private Long id;
        private Category category;
        private String name;
        private Integer price;
        private String description;
        private boolean available = true;

        public Builder() {
        }

        public Builder(Product product) {
            id = product.id;
            category = product.category;
            name = product.name;
            price = product.price;
            description = product.description;
            available = product.available;
        }

        public Product build() {
            Product product = new Product();
            product.id = id;
            product.category = category;
            product.name = name;
            product.price = price;
            product.description = description;
            product.available = available;
            return product;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public void setCategory(Category category) {
            this.category = category;
        }

        public void setName(String name) {
            this.name = name;
        }

        public void setPrice(Integer price) {
            this.price = price;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public void setAvailable(boolean available) {
            this.available = available;
        }
    }
}


