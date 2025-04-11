package com.example.product_ms.service;


import com.example.product_ms.Model.Product;
import com.example.product_ms.Repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Product addProduct(Product product){
        return productRepository.save(product);
    }

    public boolean existsByProductId(int productId) {
        return productRepository.existsById((long) productId);
    }
    public Product getProductById(Long id ) {
        return productRepository.findById(id).orElse(null);
    }

    public Product updateProduct(long id, Product product) {
        Product existingProduct = productRepository.findById(id).orElse(null);
        existingProduct.setName(product.getName());
        existingProduct.setPrice(product.getPrice());
        existingProduct.setDescription(product.getDescription());
        existingProduct.setCategory(product.getCategory());
        existingProduct.setUpdatedAt(LocalDateTime.now());
        existingProduct.setQuantity(product.getQuantity());
        existingProduct.setImage(product.getImage());
        existingProduct.setUnit(product.getUnit());
        return productRepository.save(existingProduct);
    }

    public Product updateProductPrice(Long id, Integer price){
        Product product = getProductById(id);
        product.setPrice(price);
        product.setUpdatedAt(LocalDateTime.now());
        productRepository.save(product);
        return product;
    }

    public Product updateProductQuantity(Long id, Long stock){
        Product product = getProductById(id);
        product.setQuantity(stock);
        product.setUpdatedAt(LocalDateTime.now());
        productRepository.save(product);
        return product;
    }


    public void reduceQuantity(Long id, Long quantity){
        Product product = productRepository.findById(id).orElse(null);
        if(product.getQuantity() != null && product.getQuantity() > quantity){
            product.setQuantity(product.getQuantity() - quantity);
            productRepository.save(product);
        }
    }


}
