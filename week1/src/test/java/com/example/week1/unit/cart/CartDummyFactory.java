package com.example.week1.unit.cart;

import com.example.week1.cart.Cart;
import com.example.week1.cart.CartItem;
import com.example.week1.cart.response.CartItemResponse;
import com.example.week1.product.Product;

public class CartDummyFactory {

    public static CartItemResponse getDummyCartItemResponse() {
        return new CartItemResponse(123, "MyProduct", 999, 20.0);
    }

    public static Integer getDummyProductId() {
        return 123;
    }

    public static Product getDummyProduct() {
        return new Product(getDummyProductId(), "MyProduct", 20.0, "", "", "");
    }

    public static Cart getDummyCartWithDummyItem() {
        Cart myCart = new Cart("MyUsername");
        myCart.addItem(getDummyCartItem());
        return myCart;
    }

    public static CartItem getDummyCartItem() {
        return new CartItem(123, 999);
    }
}
