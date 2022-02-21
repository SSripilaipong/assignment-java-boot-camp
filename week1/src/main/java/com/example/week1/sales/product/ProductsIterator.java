package com.example.week1.sales.product;

import java.util.Iterator;

public class ProductsIterator implements Iterator<Product> {
    public ProductsIterator(Products products) {
    }

    @Override
    public boolean hasNext() {
        return false;
    }

    @Override
    public Product next() {
        return new Product();
    }
}
