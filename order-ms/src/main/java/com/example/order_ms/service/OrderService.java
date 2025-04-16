package com.example.order_ms.service;


import com.example.order_ms.Model.*;
import com.example.order_ms.Repository.ConsumerOrderResponseRepository;
import com.example.order_ms.Repository.OrderRepository;
import com.example.order_ms.external.DTO.CartOrderRequest;
import com.example.order_ms.external.DTO.ConsumerResponse;
import com.example.order_ms.external.DTO.OrderRequest;
import com.example.order_ms.external.DTO.ProductResponse;
import com.example.order_ms.external.service.ConsumerService;
import com.example.order_ms.external.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.*;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ProductService productService;

    @Autowired
    private ConsumerService consumerService;

    @Autowired
    private ConsumerOrderResponseRepository consumerOrderResponseRepository;


    public List<Order> findAll() {
        return orderRepository.findAll();
    }

    public Order findById(Integer id) {
        return orderRepository.findById(id).orElse(null);
    }

    public int randomIdGenerator() {
        Random random = new Random();
        int randomId = Math.abs(random.nextInt());
        return randomId;
    }

    private Long generateUniqueOrderId() {
        Random random = new Random();
        long randomId;
        boolean idExists;
        do {
            randomId = Math.abs(random.nextLong());
            idExists = existsByOrderId((int) randomId);
        } while (idExists);
        return randomId;
    }

    public ConsumerOrderResponse saveCartOrder(CartOrderRequest cartOrderRequest) {
        ConsumerResponse consumerResponse = consumerService.getConsumerById(Math.toIntExact(cartOrderRequest.getConsumerId())).getBody();
        int totalCost = 0;
        Long OrderId = generateUniqueOrderId();
        int totalQuantity = cartOrderRequest.getItems().size();
        List<ConsumerOrderResponse.ProductDetails> productDetailedList = new ArrayList<>();
        for (OrderRequest orderRequest : cartOrderRequest.getItems()) {
            ProductResponse productResponse = productService.getProductById(orderRequest.getId()).getBody();
            productService.reduceQuantity(orderRequest.getId(), orderRequest.getQuantity());
            Long itemCost = orderRequest.getQuantity() * productResponse.getPrice();
            totalCost += itemCost;
            Order order = Order.builder()
                    .quantity(orderRequest.getQuantity())
                    .productId(orderRequest.getId())
                    .orderId(OrderId)
                    .orderTime(Instant.now())
                    .price(Double.valueOf(productResponse.getPrice()))
                    .build();
            orderRepository.save(order);
            ConsumerOrderResponse.ProductDetails productDetails = ConsumerOrderResponse.ProductDetails.builder()
                    .id(productResponse.getId())
                    .productName(productResponse.getName())
                    .productDescription(productResponse.getDescription())
                    .productPrice(productResponse.getPrice())
                    .productCategory(String.valueOf(productResponse.getCategory()))
                    .productId(productResponse.getProductId())
                    .quantity(productResponse.getQuantity())
                    .price(productResponse.getPrice())
                    .build();

            productDetailedList.add(productDetails);
        }
        ConsumerOrderResponse.ConsumerResponse consumerResponse1 = ConsumerOrderResponse.ConsumerResponse.builder()
                .id(consumerResponse.getId())
                .name(consumerResponse.getName())
                .phone(consumerResponse.getPhone())
                .email(consumerResponse.getEmail())
                .address(consumerResponse.getAddress())
                .build();

        ConsumerOrderResponse consumerOrderResponse =   ConsumerOrderResponse.builder()
                .orderId(OrderId)
                .orderDate(Instant.now())
                .orderStatus("ACCEPTED")
                .amount(totalCost)
                .quantity((long) totalQuantity)
                .consumerResponse(consumerResponse1)
                .productDetails(productDetailedList)
                .build();

        // Convert DTO to Entity and save to DB
        ConsumerOrderResponseEntity consumerOrderResponseEntity = mapToEntity(consumerOrderResponse);
        consumerOrderResponseRepository.save(consumerOrderResponseEntity);

        return consumerOrderResponse;

    }

    public Integer save(OrderRequest orderRequest) {
        productService.reduceQuantity(orderRequest.getId(), orderRequest.getQuantity());
        ProductResponse productResponse = productService.getProductById(orderRequest.getId()).getBody();
        int randomId;
        boolean idExists;
        do {
            randomId = randomIdGenerator();
            idExists = existsByOrderId(randomId);
        } while (idExists);
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

    public ProductResponse findProductById(Integer id) {
        ProductResponse productResponse = productService.getProductById(Long.valueOf(id)).getBody();
        return productResponse;
    }

    public ConsumerResponse getConsumerById(Integer id) {
        ConsumerResponse consumerResponse = consumerService.getConsumerById(id).getBody();
        return consumerResponse;
    }

    public OrderResponse getAllDetails(Integer id) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Order not found with ID: " + id));
        ProductResponse productResponse = productService.getProductById(order.getProductId()).getBody();
        long TotalCost = (productResponse.getPrice() * order.getQuantity());
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

    private ConsumerOrderResponseEntity mapToEntity(ConsumerOrderResponse consumerOrderResponse) {
        return ConsumerOrderResponseEntity.builder()
                .orderId(consumerOrderResponse.getOrderId())
                .orderDate(consumerOrderResponse.getOrderDate())
                .orderStatus(consumerOrderResponse.getOrderStatus())
                .amount(consumerOrderResponse.getAmount())
                .quantity(consumerOrderResponse.getQuantity())
                .consumerResponse(mapConsumerResponseToEntity(consumerOrderResponse.getConsumerResponse()))
                .productDetails(mapProductDetailsToEntity(consumerOrderResponse.getProductDetails()))
                .build();
    }

    private ConsumerResponseEntity mapConsumerResponseToEntity(ConsumerOrderResponse.ConsumerResponse consumerResponse) {
        return ConsumerResponseEntity.builder()
                .id(consumerResponse.getId())
                .name(consumerResponse.getName())
                .phone(consumerResponse.getPhone())
                .email(consumerResponse.getEmail())
                .address(consumerResponse.getAddress())
                .build();
    }

    private List<ProductDetailsEntity> mapProductDetailsToEntity(List<ConsumerOrderResponse.ProductDetails> productDetailsList) {
        return productDetailsList.stream().map(productDetails -> ProductDetailsEntity.builder()
                .productName(productDetails.getProductName())
                .productDescription(productDetails.getProductDescription())
                .productPrice(productDetails.getProductPrice())
                .productId(productDetails.getId())
                .imageUrl(productDetails.getImageUrl())
                .productCategory(productDetails.getProductCategory())
                .quantity(productDetails.getQuantity())
                .price(productDetails.getPrice())
                .build()).toList();

    }

    public Optional<ConsumerOrderResponseEntity> getCartOrderById(Integer id) {
        return consumerOrderResponseRepository.findById(id);
    }

}