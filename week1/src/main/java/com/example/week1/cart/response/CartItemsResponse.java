package com.example.week1.cart.response;

import com.example.week1.cart.Cart;
import com.example.week1.cart.CartItem;
import com.example.week1.product.Product;
import com.example.week1.product.ProductService;

import java.util.ArrayList;

public class CartItemsResponse {
    private ArrayList<CartItemResponse> items;

    public CartItemsResponse() {
        this.items = new ArrayList<>();
    }

    public static CartItemsResponse fromCart(ProductService productService, Cart cart) {
        CartItemsResponse response = new CartItemsResponse();
        for(CartItem item : cart.getItems()) {
            Product product = productService.getProduct(item.getProductId());
            response.add(new CartItemResponse(
                    product.getId(), product.getName(), item.getQuantity(), product.getPrice()));
        }
        return response;
    }

    private void add(CartItemResponse cartItemResponse) {
        items.add(cartItemResponse);
    }

    public CartItemResponse get(int index) {
        return items.get(index);
    }

    public ArrayList<CartItemResponse> getItems() {
        return items;
    }

    public void setItems(ArrayList<CartItemResponse> items) {
        this.items = items;
    }

    public Integer size() {
        return this.items.size();
    }
}
