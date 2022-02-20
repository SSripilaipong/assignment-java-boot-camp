package com.example.week1.cart;

import org.springframework.stereotype.Service;

@Service
public class CartService {
    public void addItemToMyCart(String username, CartItem item) {
    }

    public Cart getMyCart(String username) {
        return new Cart();
    }
}
