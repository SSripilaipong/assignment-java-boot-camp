package com.example.week1.unit.sales.product;

import com.example.week1.TestRequester;
import com.example.week1.sales.product.Product;
import com.example.week1.sales.product.ProductService;
import com.example.week1.sales.product.Products;
import org.junit.jupiter.api.Assertions;
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
        when(productService.searchProducts("MyKeyword")).thenReturn(ProductDummyFactory.getDummyProducts());

        Products products = requester.get(String.format("/products?keyword=%s", "MyKeyword"), Products.class).getBody();

        assert products != null;
        Assertions.assertEquals(ProductDummyFactory.getDummyProducts(), products);
    }

    @Test
    void shouldFindProductWithId() {
        when(productService.getProduct(1234)).thenReturn(ProductDummyFactory.getDummyProduct());

        Product product = requester.get(String.format("/products/%d", 1234), Product.class).getBody();

        assert product != null;
        Assertions.assertEquals(ProductDummyFactory.getDummyProduct(), product);
    }
}
