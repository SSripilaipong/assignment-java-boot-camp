package com.example.week1.product;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

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

    // for JSON serialization
    public List<Product> getProducts() {
        return products;
    }
}
