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

/**
 * @param productCS
 * @param code
 * updateProducts is used to update the stock details once the order is placed.
 */
public ProductCS updateProducts(ProductCS productCS, String code) {
	
	ProductCS obj = inventoryRepository.findByCode(code);
	obj.setStock(productCS.getStock());
	return inventoryRepository.save(obj);
}





}
