package com.example.week1.unit.product;

import com.example.week1.product.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ProductServiceTest {

    @Mock
    private ProductRepository productRepository;

    @Test
    void shouldReturnProductsWithKeywordInNames() {
        ProductService productService = new ProductService();
        productService.setProductRepository(productRepository);
        List<Product> matchedProducts = new ArrayList<>();
        matchedProducts.add(new Product(0, "A", 999.0));
        matchedProducts.add(new Product(1, "B", 888.0));
        when(productRepository.findByNameContaining("MyKeyword")).thenReturn(matchedProducts);

        Products products = productService.searchProducts("MyKeyword");

        assertEquals(2, products.size());
        assertEquals("A", products.get(0).getName());
        assertEquals("B", products.get(1).getName());
    }

    @Test
    void shouldAddNewProduct() {
        ProductService productService = new ProductService();
        productService.setProductRepository(productRepository);
        ArgumentCaptor<Product> arg = ArgumentCaptor.forClass(Product.class);

        Product newProduct = new Product(0, "MyProduct", 999.0);
        productService.addNewProduct(newProduct);

        verify(productRepository).save(newProduct);
    }
}
