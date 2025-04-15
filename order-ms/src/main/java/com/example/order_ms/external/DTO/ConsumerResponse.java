package com.example.order_ms.external.DTO;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ConsumerResponse {
    private Integer id;
    private String name;
    private String phone;
    private String email;
    private String address;
}
