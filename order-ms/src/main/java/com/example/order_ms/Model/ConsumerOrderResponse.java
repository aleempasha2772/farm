package com.example.order_ms.Model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ConsumerOrderResponse {
    private Long id;
    private long orderId;
    private Instant orderDate;
    private String orderStatus;
    private long amount;
    private Long quantity;
    private List<ProductDetails> productDetails;
    private ConsumerResponse consumerResponse;

    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class ProductDetails {

        private String productName;
        private String productDescription;
        private int productPrice;
        private int id;
        private String imageUrl;
        private String productCategory;
        private long productId;
        private long quantity;
        private long price;
    }
    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class ConsumerResponse {
        private Integer id;
        private String name;
        private String phone;
        private String email;
        private String address;
    }
}
