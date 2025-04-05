package com.example.product_ms.Controller;

import com.example.product_ms.Model.Product;
import com.example.product_ms.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Random;

@Controller
@RequestMapping("api/v1")
public class ProductController {

    @Autowired
    private ProductService productService;


    public int randomIdGenerator(){
        Random random = new Random();
        int randomId = Math.abs(random.nextInt());
        return randomId;
    }

    @PostMapping("/create-product")
    public ResponseEntity<Product> addProduct(@RequestBody Product product) {
        int randomId;
        boolean idExists;
        do{
            randomId = randomIdGenerator();
            idExists = productService.existsByProductId(randomId);
        }while (idExists);
        product.setProductId(randomId);
        Product newProduct = productService.addProduct(product);
        return ResponseEntity.ok(newProduct);
    }
    @GetMapping("/get-products")
    public ResponseEntity<List<Product>> getAllProducts(){
        return ResponseEntity.ok(productService.getAllProducts());
    }

    @GetMapping("/get-product/{productId}")
    public ResponseEntity<Product> getProductById(@PathVariable Long productId){
        return ResponseEntity.ok(productService.getProductById(productId));
    }


    @PutMapping("/update/{productId}")
    public ResponseEntity<Product> updateProduct(@PathVariable Long productId,@RequestBody Product product){
        return ResponseEntity.ok(productService.updateProduct(productId,product));
    }

    @PutMapping("/quantity/{productId}")
    public ResponseEntity<Product> updateProductStock(@PathVariable Long productId,@RequestParam Integer quantity){
        return ResponseEntity.ok(productService.updateProductQuantity(productId,quantity));
    }



   @PutMapping("/price/{productId}")
   public ResponseEntity<Product> updateProductPrice(@PathVariable Long productId, @RequestParam int newPrice){
        return ResponseEntity.ok(productService.updateProductPrice(productId,newPrice));
    }







}
