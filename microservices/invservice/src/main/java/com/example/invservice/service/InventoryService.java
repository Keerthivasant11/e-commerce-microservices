package com.example.invservice.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.example.invservice.entity.ProductCS;
import com.example.invservice.repository.InventoryRepository;

@Service
public class InventoryService {
	
	@Autowired
	private InventoryRepository inventoryRepository;
	
public Optional<ProductCS> getProducts(String code) {
		
		
		return Optional.of(inventoryRepository.findByCode(code));
	}



public ProductCS updateProducts(ProductCS productCS) {
	
	ProductCS obj = inventoryRepository.findByCode(productCS.getCode());
	Long a = obj.getStock();
	Long b = productCS.getStock();
	Long c = a-b;
	obj.setStock(c);
	
	return inventoryRepository.save(obj);
}

}
