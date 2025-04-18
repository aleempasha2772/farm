package com.example.order_ms.external.service;


import com.example.order_ms.external.DTO.ProductResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "product-ms/api/v1")
public interface ProductService {
    @PutMapping("/reduce-quantity/{id}")
    ResponseEntity<Void> reduceQuantity(@PathVariable Long id, @RequestParam Long quantity);
    @GetMapping("/get-product/{productId}")
    ResponseEntity<ProductResponse> getProductById(@PathVariable Long productId);
}
