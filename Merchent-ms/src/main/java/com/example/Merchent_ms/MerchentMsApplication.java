package com.example.Merchent_ms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class MerchentMsApplication {

	public static void main(String[] args) {
		SpringApplication.run(MerchentMsApplication.class, args);
	}

}
