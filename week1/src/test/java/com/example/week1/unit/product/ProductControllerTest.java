package com.example.week1.unit.product;

import com.example.week1.TestRequester;
import com.example.week1.product.Product;
import com.example.week1.product.ProductService;
import com.example.week1.product.Products;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ProductControllerTest {

    @Autowired
    private TestRequester requester;

    @MockBean
    private ProductService productService;

    @Test
    void shouldReturnProductsWithKeywordInNames() {
        Products matchedProducts = new Products();
        matchedProducts.add(new Product("A"));
        matchedProducts.add(new Product("B"));
        when(productService.searchProducts("MyKeyword")).thenReturn(matchedProducts);

        Products products =
                requester.get(String.format("/products?keyword=%s", "MyKeyword"), Products.class).getBody();

        assert products != null;
        assertEquals("A", products.get(0).getName());
        assertEquals("B", products.get(1).getName());
    }
}
