package com.example.week1.cart;

import java.io.Serializable;
import java.util.Objects;

public class CartItem implements Serializable {
    private Integer productId;
    private Integer quantity;

    public CartItem() {
    }

    public CartItem(Integer productId, Integer quantity) {
        this.productId = productId;
        this.quantity = quantity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CartItem cartItem = (CartItem) o;
        return Objects.equals(productId, cartItem.productId) && Objects.equals(quantity, cartItem.quantity);
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }
}
