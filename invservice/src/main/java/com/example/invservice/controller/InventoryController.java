package com.example.invservice.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
	@PutMapping("/{code}")
	public ProductCS updateMethod(@PathVariable("code") String code,@RequestBody ProductCS productCS )
	{
		
		return inventoryService.updateProducts(productCS, code);
	}
	
}
