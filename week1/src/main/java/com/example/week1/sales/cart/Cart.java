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
    private Integer addressId;
    private Integer paymentMethodId;

    public Cart() {
        items = new ArrayList<>();
    }

    public Cart(String username) {
        this.username = username;
        items = new ArrayList<>();
    }

    public Cart(String username, Integer addressId, Integer paymentMethodId) {
        this.username = username;
        this.addressId = addressId;
        this.paymentMethodId = paymentMethodId;
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
        return Objects.equals(addressId, cart.addressId) && Objects.equals(username, cart.username) && Objects.equals(items, cart.items);
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

    public Integer getAddressId() {
        return addressId;
    }

    public void setAddressId(Integer addressId) {
        this.addressId = addressId;
    }

    public Integer getPaymentMethodId() {
        return paymentMethodId;
    }

    public void setPaymentMethodId(Integer paymentMethodId) {
        this.paymentMethodId = paymentMethodId;
    }

}
