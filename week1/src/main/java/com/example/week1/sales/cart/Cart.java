package com.example.week1.sales.cart;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import java.util.ArrayList;
import java.util.Objects;

@Entity
public class Cart {
    @Id
    private String username;
    @Lob
    private ArrayList<CartItem> items;

    public Cart() {
        items = new ArrayList<>();
    }

    public Cart(String username) {
        this.username = username;
        items = new ArrayList<>();
    }

    public void addItem(CartItem item) {
        items.add(item);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cart cart = (Cart) o;
        return Objects.equals(username, cart.username) && Objects.equals(items, cart.items);
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public ArrayList<CartItem> getItems() {
        return items;
    }

    public void setItems(ArrayList<CartItem> items) {
        this.items = items;
    }

}
