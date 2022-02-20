package com.example.week1.cart;

import java.util.ArrayList;

public class CartSummary {
    private ArrayList<CartItem> items;

    public CartSummary() {
        items = new ArrayList<>();
    }

    public void addItem(CartItem cartItem) {
        items.add(cartItem);
    }

    public ArrayList<CartItem> getItems() {
        return items;
    }

    public void setItems(ArrayList<CartItem> items) {
        this.items = items;
    }
}
