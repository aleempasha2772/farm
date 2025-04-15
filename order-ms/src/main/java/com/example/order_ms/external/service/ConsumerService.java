package com.example.order_ms.external.service;


import com.example.order_ms.external.DTO.ConsumerResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@FeignClient("consumer-ms/api/v1")
public interface ConsumerService {


    @GetMapping("get-consumer/{id}")
    ResponseEntity<ConsumerResponse> getConsumerById(@PathVariable int id);
}
