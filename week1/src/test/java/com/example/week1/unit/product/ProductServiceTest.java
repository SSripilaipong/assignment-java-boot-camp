package com.example.week1.unit.product;

import com.example.week1.product.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static com.example.week1.unit.product.ProductDummyFactory.*;
import static com.example.week1.unit.product.ProductDummyFactory.getDummyProductList;
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
        when(productRepository.findByNameContaining("MyKeyword")).thenReturn(getDummyProductList());

        Products products = productService.searchProducts("MyKeyword");

        assertEquals(getDummyProducts(), products);
    }

    @Test
    void shouldAddNewProduct() {
        ProductService productService = new ProductService();
        productService.setProductRepository(productRepository);

        Product newProduct = getDummyProduct();
        productService.addNewProduct(newProduct);

        verify(productRepository).save(newProduct);
    }

    @Test
    void shouldFindProductById() {
        ProductService productService = new ProductService();
        productService.setProductRepository(productRepository);
        when(productRepository.findById(1234)).thenReturn(Optional.of(getDummyProduct()));

        Product product = productService.getProduct(1234);

        assertEquals(getDummyProduct(), product);
    }
}
