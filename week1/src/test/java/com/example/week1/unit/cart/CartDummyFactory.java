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

    public static Product getDummyProductA() {
        return new Product(1, "A", 20.0, "", "", "");
    }

    public static Product getDummyProductB() {
        return new Product(2, "B", 10.0, "", "", "");
    }

    public static Cart getDummyCartWithProductAAndB() {
        Cart cart = new Cart("MyUsername");
        cart.addItem(new CartItem(getDummyProductA().getId(), 2));
        cart.addItem(new CartItem(getDummyProductB().getId(), 3));
        return cart;
    }

    public static Double getTotalPriceOfDummyCartWithProductAAndB() {
        return 20.0*2 + 10.0*3;
    }

    public static CartItemResponse getDummyCartItemResponseA() {
        return new CartItemResponse(1, "A", 2, 20.0);
    }

    public static CartItemResponse getDummyCartItemResponseB() {
        return new CartItemResponse(2, "B", 3, 10.0);
    }
}
