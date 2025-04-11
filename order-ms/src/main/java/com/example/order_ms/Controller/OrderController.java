package com.example.order_ms.Controller;


import com.example.order_ms.Model.Order;
import com.example.order_ms.Model.OrderResponse;
import com.example.order_ms.external.DTO.OrderRequest;
import com.example.order_ms.external.DTO.ProductResponse;
import com.example.order_ms.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Random;

@Controller
@RequestMapping("api/v1")
public class OrderController {

    @Autowired
    private OrderService orderService;


    @PostMapping("/save-order")
    public ResponseEntity<Integer> saveOrder(@RequestBody OrderRequest orderRequest){
        Integer orderId = orderService.save(orderRequest);
        return new ResponseEntity<>(orderId, HttpStatus.OK);
    }

    @GetMapping("get-orders")
    public ResponseEntity<List<Order>> getAllOrders(){
        return ResponseEntity.ok(orderService.findAll());
    }



    @GetMapping("/product-details/{id}")
    public ResponseEntity<ProductResponse> getProductById(@PathVariable Integer id){
        //ProductResponse productResponse = orderService.findProductById(id);
        return ResponseEntity.ok(orderService.findProductById(id));
    }


    @GetMapping("/get-all-details/{id}")
    public ResponseEntity<OrderResponse> getAllDetails(@PathVariable Integer id){
        return ResponseEntity.ok(orderService.getAllDetails(id));
    }


}
