package com.example.week1.cart.response;

import com.example.week1.cart.CartItem;
import com.example.week1.cart.CartSummary;
import com.example.week1.product.Product;
import com.example.week1.product.ProductService;

import java.util.ArrayList;

public class CartSummaryResponse {
    private ArrayList<CartItemResponse> items;

    public CartSummaryResponse() {
        items = new ArrayList<>();
    }

    public static CartSummaryResponse fromCartSummary(ProductService productService, CartSummary summary) {
        CartSummaryResponse response = new CartSummaryResponse();
        for(CartItem item : summary.getItems()) {
            Product product = productService.getProduct(item.getProductId());
            response.addItem(new CartItemResponse(  // TODO: create method ofProduct(product, quantity)
                    product.getId(), product.getName(), item.getQuantity(), product.getPrice()));
        }
        return response;
    }

    public Double getTotalPrice() {
        return null;
    }

    private void addItem(CartItemResponse cartItemResponse) {
        items.add(cartItemResponse);
    }

    public ArrayList<CartItemResponse> getItems() {
        return items;
    }

    public void setItems(ArrayList<CartItemResponse> items) {
        this.items = items;
    }
}
