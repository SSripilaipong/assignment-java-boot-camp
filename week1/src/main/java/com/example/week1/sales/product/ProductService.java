package com.example.week1.sales.product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    public Products searchProducts(String keyword) {
        List<Product> productList = productRepository.findByNameContaining(keyword);
        Products result = new Products();
        for(Product product: productList) {
            result.add(product);
        }
        return result;
    }

    public void setProductRepository(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public void addNewProduct(Product newProduct) {
        productRepository.save(newProduct);
    }

    public Product getProduct(int id) {
        // Actually, when result is not present, should throw exception
        return productRepository.findById(id).orElse(null);
    }
}
