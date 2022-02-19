package com.example.week1.product;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Product {
    @Id
    private Integer id;
    private String name;
    private Double price;

    public Product() {
    }

    public Product(Integer id, String name, Double price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object that) {
        if(that instanceof Product) {
            Product q = (Product)that;
            return q.id.equals(this.id) && q.name.equals(this.name) && q.price.equals(this.price);
        }
        return false;
    }
}
