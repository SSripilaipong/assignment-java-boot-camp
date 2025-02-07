package com.example.week1.sales.cart.request;

import com.example.week1.sales.cart.CartItem;
import com.example.week1.rest.JsonConvertible;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Objects;

@Getter @Setter @NoArgsConstructor
public class CartItemAddingRequest implements JsonConvertible {
    private Integer productId;
    private Integer quantity;

    public CartItemAddingRequest(Integer productId, Integer quantity) {
        this.productId = productId;
        this.quantity = quantity;
    }

    @Override
    public String toJsonString() {
        JSONObject json = new JSONObject();
        try {
            json.put("productId", productId);
            json.put("quantity", quantity);
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
        return json.toString();
    }

    public CartItem toCartItem() {
        return new CartItem(productId, quantity);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CartItemAddingRequest that = (CartItemAddingRequest) o;
        return Objects.equals(productId, that.productId) && Objects.equals(quantity, that.quantity);
    }
}
