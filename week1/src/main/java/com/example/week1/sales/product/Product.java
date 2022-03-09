package com.example.week1.sales.product;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Objects;

@Entity
@Getter @Setter
public class Product {
    @Id
    private Integer id;
    private String name;
    private Double price;
    private String description;
    private String brand;
    private String occasion;

    public Product() {
    }

    public Product(Integer id, String name, Double price, String description, String brand, String occasion) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.description = description;
        this.brand = brand;
        this.occasion = occasion;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return Objects.equals(id, product.id) && Objects.equals(name, product.name) &&
                Objects.equals(price, product.price);
    }

}
