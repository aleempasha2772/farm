package com.example.order_ms.Model;


import com.example.order_ms.external.DTO.ProductResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderResponse {

    private long orderId;
    private Instant orderDate;
    private String orderStatus;
    private long amount;
    private Long quantity;
    private ProductDetails productDetails;

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
}
