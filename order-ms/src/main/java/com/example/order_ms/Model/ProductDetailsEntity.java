package com.example.order_ms.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "product_details")
public class ProductDetailsEntity {


    private Long id;

    private String productName;
    private String productDescription;
    private int productPrice;
    @Id
    private int productId;
    private String imageUrl;
    private String productCategory;
    private long quantity;
    private long price;
}