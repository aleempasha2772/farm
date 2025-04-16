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
@Table(name = "consumer_response")
public class ConsumerResponseEntity {

    @Id
    private Integer id;
    private String name;
    private String phone;
    private String email;
    private String address;
}