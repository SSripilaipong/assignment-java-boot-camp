package com.example.week1.sales.cart.response;

import com.example.week1.sales.product.Product;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Objects;

@Getter @Setter @NoArgsConstructor
public class CartItemResponse {
    private Integer productId;
    private String productName;
    private Integer quantity;
    private Double pricePerUnit;

    public CartItemResponse(int productId, String productName, int quantity, double pricePerUnit) {
        this.productId = productId;
        this.productName = productName;
        this.quantity = quantity;
        this.pricePerUnit = pricePerUnit;
    }

    public static CartItemResponse ofProduct(Product product, Integer quantity) {
        return new CartItemResponse(product.getId(), product.getName(), quantity, product.getPrice());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CartItemResponse that = (CartItemResponse) o;
        return Objects.equals(productId, that.productId) && Objects.equals(productName, that.productName) && Objects.equals(quantity, that.quantity) && Objects.equals(pricePerUnit, that.pricePerUnit);
    }

}
