package com.example.consumer_ms.service;


import com.example.consumer_ms.model.Consumer;
import com.example.consumer_ms.repository.ConsumerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ConsumerService {

    @Autowired
    private ConsumerRepository consumerRepository;

    public List<Consumer> getAllConsumer(){
        return consumerRepository.findAll();
    }

    public Consumer getConsumerById(int id){
        return consumerRepository.findById(id).get();
    }

    public Consumer saveConsumer(Consumer consumer){
        return consumerRepository.save(consumer);
    }
}
