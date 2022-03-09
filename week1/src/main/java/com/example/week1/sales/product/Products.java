package com.example.week1.sales.product;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Getter @Setter
public class Products implements Iterable<Product> {
    private final List<Product> products;

    public Products() {
        products = new ArrayList<>();
    }

    @Override
    public Iterator<Product> iterator() {
        return new ProductsIterator(this);
    }

    public int size() {
        return products.size();
    }

    public void add(Product product) {
        products.add(product);
    }

    public Product get(int index) {
        return products.get(index);
    }

    @Override
    public boolean equals(Object other) {
        if(other instanceof Products) {
            return this.products.equals(((Products) other).products);
        }
        return false;
    }
}
