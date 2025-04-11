package com.example.order_ms.external.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;



@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductResponse {
    private Integer id;
    private int productId;
    private String name;
    private String description;
    private Integer price;
    private Long Quantity;
    private Integer unit;
    private String image;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private ProductCategory category;
    public enum ProductCategory {
        FRUIT("Fruit", "per kg"),
        VEGETABLE("Vegetable", "per bunch"),
        // Add future categories easily
        HERB("Herb", "per packet");

        private final String displayName;
        private final String pricingUnit;

        ProductCategory(String displayName, String pricingUnit) {
            this.displayName = displayName;
            this.pricingUnit = pricingUnit;
        }

        // Getters
        public String getDisplayName() { return displayName; }
        public String getPricingUnit() { return pricingUnit; }
    }
}
