package com.example.week1.unit.product;

import com.example.week1.product.Product;
import com.example.week1.product.Products;

import java.util.ArrayList;
import java.util.List;

public class ProductDummyFactory {
    public static List<Product> getDummyProductList() {
        List<Product> productList = new ArrayList<>();
        productList.add(new Product(0, "A", 999.0));
        productList.add(new Product(1, "B", 888.0));
        return productList;
    }

    public static Product getDummyProduct() {
        return new Product(0, "MyProduct", 999.0);
    }

    public static Products getDummyProducts() {
        Products products = new Products();
        for(Product product : getDummyProductList()) {
            products.add(product);
        }
        return products;
    }
}
