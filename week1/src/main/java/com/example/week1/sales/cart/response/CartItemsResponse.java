package com.example.week1.sales.cart.response;

import com.example.week1.sales.cart.Cart;
import com.example.week1.sales.cart.CartItem;
import com.example.week1.sales.product.Product;
import com.example.week1.sales.product.ProductService;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

@Getter @Setter
public class CartItemsResponse {
    private ArrayList<CartItemResponse> items;

    public CartItemsResponse() {
        this.items = new ArrayList<>();
    }

    public static CartItemsResponse fromCart(ProductService productService, Cart cart) {
        CartItemsResponse response = new CartItemsResponse();
        for(CartItem item : cart.getItems()) {
            Product product = productService.getProduct(item.getProductId());
            response.add(CartItemResponse.ofProduct(product, item.getQuantity()));
        }
        return response;
    }

    private void add(CartItemResponse cartItemResponse) {
        items.add(cartItemResponse);
    }

    public CartItemResponse get(int index) {
        return items.get(index);
    }

    public Integer size() {
        return this.items.size();
    }
}
