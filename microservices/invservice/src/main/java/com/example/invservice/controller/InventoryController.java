package com.example.invservice.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.invservice.entity.ProductCS;
import com.example.invservice.service.InventoryService;


@RestController
@RequestMapping("/inventory")
public class InventoryController {
	@Autowired
	private InventoryService inventoryService;
	@GetMapping("/{code}")
	public Optional<ProductCS> getMethod(@PathVariable("code") String code)
	{
		return inventoryService.getProducts(code);
	}
	
	@PutMapping
	@KafkaListener(topics="deduction")
	public ProductCS updateMethod(ProductCS productCS)
	{
		
		return inventoryService.updateProducts(productCS);
	}
}
