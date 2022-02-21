package com.example.week1.unit.sales.product;

import com.example.week1.sales.product.Product;
import com.example.week1.sales.product.ProductRepository;
import com.example.week1.sales.product.ProductService;
import com.example.week1.sales.product.Products;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ProductServiceTest {

    @Mock
    private ProductRepository productRepository;

    @Test
    void shouldReturnProductsWithKeywordInNames() {
        when(productRepository.findByNameContaining("MyKeyword")).thenReturn(ProductDummyFactory.getDummyProductList());

        Products products = getProductServiceWithMock().searchProducts("MyKeyword");

        Assertions.assertEquals(ProductDummyFactory.getDummyProducts(), products);
    }

    @Test
    void shouldAddNewProduct() {
        Product newProduct = ProductDummyFactory.getDummyProduct();
        getProductServiceWithMock().addNewProduct(newProduct);

        verify(productRepository).save(newProduct);
    }

    @Test
    void shouldFindProductById() {
        when(productRepository.findById(1234)).thenReturn(Optional.of(ProductDummyFactory.getDummyProduct()));

        Product product = getProductServiceWithMock().getProduct(1234);

        Assertions.assertEquals(ProductDummyFactory.getDummyProduct(), product);
    }

    private ProductService getProductServiceWithMock() {
        ProductService productService = new ProductService();
        productService.setProductRepository(productRepository);
        return productService;
    }
}
