package com.example.week1.product;

import java.util.Iterator;

public class Products implements Iterable<Product> {
    @Override
    public Iterator<Product> iterator() {
        return new ProductsIterator(this);
    }

    public int size() {
        return 0;
    }
}
