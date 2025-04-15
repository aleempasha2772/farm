package com.example.order_ms.external.DTO;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CartOrderRequest {
    private Long consumerId;
    private List<OrderRequest> items;
}
