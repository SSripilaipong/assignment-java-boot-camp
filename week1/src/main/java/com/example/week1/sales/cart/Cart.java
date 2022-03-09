package com.example.week1.sales.cart;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import java.util.ArrayList;
import java.util.Objects;

@Entity
@Getter @Setter
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
        return Objects.equals(paymentMethodId, cart.paymentMethodId) && Objects.equals(addressId, cart.addressId) && Objects.equals(username, cart.username) && Objects.equals(items, cart.items);
    }

}
