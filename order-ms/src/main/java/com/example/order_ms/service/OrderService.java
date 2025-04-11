package com.example.order_ms.service;


import com.example.order_ms.Model.Order;
import com.example.order_ms.Model.OrderResponse;
import com.example.order_ms.Repository.OrderRepository;
import com.example.order_ms.external.DTO.OrderRequest;
import com.example.order_ms.external.DTO.ProductResponse;
import com.example.order_ms.external.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.Random;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ProductService productService;


    public List<Order> findAll(){
        return orderRepository.findAll();
    }
    public Order findById(Integer id){
        return orderRepository.findById(id).orElse(null);
    }

    public int randomIdGenerator(){
        Random random = new Random();
        int randomId = Math.abs(random.nextInt());
        return randomId;
    }

    public Integer save(OrderRequest orderRequest){
        productService.reduceQuantity(orderRequest.getId(),orderRequest.getQuantity());
        ProductResponse productResponse = productService.getProductById(orderRequest.getId()).getBody();
        int randomId;
        boolean idExists;
        do{
            randomId = randomIdGenerator();
            idExists = existsByOrderId(randomId);
        }while (idExists);
        Order order = Order.builder()
                .quantity(orderRequest.getQuantity())
                .productId(orderRequest.getId())
                .orderId((long) randomId)
                .orderTime(Instant.now())
                .price(Double.valueOf(productResponse.getPrice()))
                .build();

        orderRepository.save(order);
        return order.getId();
    }

    public boolean existsByOrderId(int orderId) {
        return orderRepository.existsById(orderId);
    }

    public ProductResponse findProductById(Integer id){
        ProductResponse productResponse = productService.getProductById(Long.valueOf(id)).getBody();
        return productResponse;
    }

    public OrderResponse getAllDetails(Integer id){
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Order not found with ID: " + id));
        ProductResponse productResponse = productService.getProductById(order.getProductId()).getBody();
        long TotalCost = (productResponse.getPrice()*order.getQuantity());
System.out.println(TotalCost);
        // Build ProductDetails
        OrderResponse.ProductDetails productDetails = OrderResponse.ProductDetails.builder()
                .productName(productResponse.getName())
                .productDescription(productResponse.getDescription())
                .productPrice(productResponse.getPrice())
                .id(productResponse.getId())
                .imageUrl(productResponse.getImage())
                .productCategory(productResponse.getCategory() != null ? productResponse.getCategory().name() : null)
                .productId(productResponse.getProductId())
                .quantity(productResponse.getQuantity()) // From Order, not ProductResponse
                .price(productResponse.getPrice()) // From Order, not ProductResponse
                .build();

        return OrderResponse.builder()
                .orderId(order.getOrderId())
                .orderStatus("Accepted")
                .orderDate(order.getOrderTime())
                .amount(TotalCost)
                .quantity(order.getQuantity())
                .productDetails(productDetails)
                .build();

    }
}

