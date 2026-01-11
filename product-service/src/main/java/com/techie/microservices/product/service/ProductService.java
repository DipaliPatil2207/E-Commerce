package com.techie.microservices.product.service;

import com.techie.microservices.product.model.Product;
import com.techie.microservices.product.repository.ProductRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class ProductService {
    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Product createProduct(Product product) {
        productRepository.save(product);
        log.info("Product created successfully");
        return product;
    }

    public List<Product> getAllProducts() {
        log.info("Return product list.");
        return productRepository.findAll();
    }
}
