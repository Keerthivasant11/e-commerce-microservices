package com.example.invservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.kafka.annotation.KafkaListener;

import com.example.invservice.entity.ProductCS;
import com.example.invservice.repository.InventoryRepository;

@SpringBootApplication
@EnableDiscoveryClient
public class InvserviceApplication {
		public static void main(String[] args) {
			SpringApplication.run(InvserviceApplication.class, args);
		}
		@Autowired
		private InventoryRepository inventoryRepository;
		
		
	    @KafkaListener(topics="StockDetails")
		public void stockDetails(ProductCS productCS) {
	    	inventoryRepository.save(productCS);
		}
	    
	    @KafkaListener(topics="deduction")
	    
	    public void stockDeduction(ProductCS productCS) {
	    	inventoryRepository.save(productCS);
	    }

	}


