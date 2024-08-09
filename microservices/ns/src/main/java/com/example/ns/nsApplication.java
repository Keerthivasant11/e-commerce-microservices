package com.example.ns;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.kafka.annotation.KafkaListener;


@SpringBootApplication
@EnableDiscoveryClient
public class nsApplication {

	public static void main(String[] args) {
		
		
		SpringApplication.run(nsApplication.class, args);
	}
	Logger logger = LoggerFactory.getLogger(nsApplication.class);
	@KafkaListener(topics="notificationItem")
	public void handleNotification(PlaceOrder placeOrder) {
		logger.info("Received order for - {}",placeOrder.getCode());
	}
}
	
