package com.example.consumer_ms.controller;


import com.example.consumer_ms.model.Consumer;
import com.example.consumer_ms.service.ConsumerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/")
public class ConsumerController {

    @Autowired
    private ConsumerService consumerService;

    @GetMapping("get-consumers")
    public ResponseEntity<List<Consumer>> getAllConsumers() {
        return ResponseEntity.ok(consumerService.getAllConsumer());
    }

    @PostMapping("save-consumer")
    public ResponseEntity<Consumer> saveConsumer(@RequestBody Consumer consumer) {
        return ResponseEntity.ok(consumerService.saveConsumer(consumer));
    }

    @GetMapping("get-consumer/{id}")
    public ResponseEntity<Consumer> getConsumerById(@PathVariable int id) {
        return ResponseEntity.ok(consumerService.getConsumerById(id));
    }

}
