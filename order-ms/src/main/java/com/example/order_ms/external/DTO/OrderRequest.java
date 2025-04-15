package com.example.order_ms.external.DTO;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Builder
public class OrderRequest {
    private long id;
    private long quantity;

    public OrderRequest(long id, long quantity) {
        this.id = id;
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "OrderRequest{" +
                "id=" + id +
                ", quantity=" + quantity +
                '}';
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getQuantity() {
        return quantity;
    }

    public void setQuantity(long quantity) {
        this.quantity = quantity;
    }
}
