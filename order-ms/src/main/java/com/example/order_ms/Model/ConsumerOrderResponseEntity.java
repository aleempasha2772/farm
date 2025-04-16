package com.example.order_ms.Model;
import jakarta.persistence.*;
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
@Entity
@Table(name = "consumer_order_response")
public class ConsumerOrderResponseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private long orderId;

    @Column(nullable = false)
    private Instant orderDate;

    @Column(nullable = false)
    private String orderStatus;

    @Column(nullable = false)
    private long amount;

    @Column(nullable = false)
    private Long quantity;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "consumer_order_id")
    private List<ProductDetailsEntity> productDetails;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "consumer_id", referencedColumnName = "id")
    private ConsumerResponseEntity consumerResponse;

}
