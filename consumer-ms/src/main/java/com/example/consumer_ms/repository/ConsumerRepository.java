package com.example.consumer_ms.repository;

import com.example.consumer_ms.model.Consumer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConsumerRepository extends JpaRepository<Consumer, Integer> {

}
