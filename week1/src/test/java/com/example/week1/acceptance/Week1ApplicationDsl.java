package com.example.week1.acceptance;

import com.example.week1.cart.response.CartItemsResponse;
import com.example.week1.product.Product;
import com.example.week1.product.Products;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Week1ApplicationDsl {

    @Autowired
    private Week1ApplicationRestApiDriver driver;

    public void userLogin(String username, String password) {
        driver.loginWithUsernameAndPassword(username, password);
    }

    public boolean confirmUserLogin(String username) {
        String currentUsername = driver.getCurrentUsername();
        return username.equals(currentUsername);
    }

    public Products searchForProductsWithKeyword(String keyword) {
        return driver.searchForProductWithKeyword(keyword);
    }

    public boolean confirmAllProductNamesContainKeyword(Products products, String keyword) {
        for(Product product: products) {
            if(!product.getName().contains(keyword)) {
                return false;
            }
        }
        return true;
    }

    public Product getProductDetail(int id) {
        return driver.getProductDetailById(id);
    }

    public void addItemToCart(int productId, int quantity) {
        driver.addItemToCartWithProductId(productId, quantity);
    }

    public CartItemsResponse getCartItems() {
        return new CartItemsResponse();
    }
}
