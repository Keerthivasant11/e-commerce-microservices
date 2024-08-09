package com.example.ps.controller;



import java.net.ConnectException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.retry.annotation.Recover;
import org.springframework.retry.annotation.Retryable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.ps.entity.Product;
import com.example.ps.service.ProductCN;
import com.example.ps.service.ProductService;

@RestController
@RequestMapping("/Product")
public class Controller {
	@Autowired
	private ProductService productservice;
	@PostMapping("/save")
	@ResponseStatus(HttpStatus.CREATED)
	public ProductCN createMethod(@RequestBody Product product )
	{
		return productservice.createProducts(product);
	}
	
	
	@GetMapping("/{code}")
	public Optional<ProductCN> getMethod(@PathVariable("code") String code) 
	{
		return productservice.getProducts(code);
	}
	

}
