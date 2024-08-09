package com.example.os.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.retry.annotation.Recover;
import org.springframework.retry.annotation.Retryable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.os.entity.OrderModel;
import com.example.os.entity.PlaceOrder;
import com.example.os.service.OrderService;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;

@RestController
@RequestMapping("/order")
public class OrderController {
	
	@Autowired
	private OrderService orderService;
	
	@GetMapping("/{code}")
	public OrderModel getMethod(@PathVariable("code") String code) 
	{
		return orderService.getProducts(code);
	} 
	
	
	
	@PostMapping("/order save")
	@CircuitBreaker(name ="rithu", fallbackMethod="fallbackMethod")
	public String placeOrder(@RequestBody PlaceOrder placeOrder) {
		if (orderService.placeOrder(placeOrder)) {
			return "Order placed successfully";
		}
		else {
		return "Order not in stock";
		}
	}
	public String fallbackMethod(PlaceOrder placeOrder, RuntimeException runtimeException) {
	    return "Please try after sometime";
	    
	  }
	

}
