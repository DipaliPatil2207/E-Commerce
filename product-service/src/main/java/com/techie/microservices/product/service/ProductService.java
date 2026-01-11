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
        Product saveProduct = productRepository.save(product);
        System.out.println("Product created successfully: {}"+saveProduct);
//        log.info("Product created successfully: {}", saveProduct);
        return saveProduct;
    }

    public List<Product> getAllProducts() {
//        log.info("Return product list.");
        return productRepository.findAll();
    }
}
