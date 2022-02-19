package com.example.week1.product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class ProductController {
    @Autowired
    private ProductService productService;

    @GetMapping("/products")
    @ResponseBody
    public Products searchProductByKeywords(@RequestParam String keyword) {
        return productService.searchProducts(keyword);
    }

    @GetMapping("/products/{id}")
    @ResponseBody
    public Product getProductDetailById(@PathVariable Integer id) {
        return productService.getProduct(id);
    }
}
