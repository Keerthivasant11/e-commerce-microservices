package com.example.os.service;

import java.net.ConnectException;
import java.nio.charset.StandardCharsets;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.support.BasicAuthenticationInterceptor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.retry.annotation.*;

import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;

import com.example.os.entity.OrderModel;
import com.example.os.entity.PlaceOrder;
import com.example.os.repository.OrderRepository;
import com.example.os.repository.OrderRepository2;

@Service
public class OrderService {
	@Autowired
	RestTemplate restTemplate;
	@Autowired
	private OrderRepository orderRepository;
	
	@Autowired
	private KafkaTemplate<String,OrderModel> kafkaTemplate;
	
	@Autowired
	private OrderRepository2 orderRepository2;
	
	

	

	
        /**
         * @param code
         * getProducts method is used to display the product code, name and 
         * stock by combining the data obtained from product service and inventory service
         */
	@Retryable(
            value = {RuntimeException.class },
            maxAttempts = 3,
            backoff = @Backoff(delay = 1000))
        public OrderModel getProducts(String code) {
		System.out.println("Retrying now");
        restTemplate.getInterceptors().add(new BasicAuthenticationInterceptor("rithu", "rithanya"));
		OrderModel ps = restTemplate.getForObject("http://localhost:6000/Product/{code}", OrderModel.class,code); 
		OrderModel is = restTemplate.getForObject("http://localhost:6000/inventory/{code}", OrderModel.class,code);
		OrderModel os = new OrderModel();
		os.setCode(ps.getCode());
		os.setName(ps.getName());
		os.setStock(is.getStock());
		orderRepository.save(os);
		return orderRepository.findByCode(code);
	}
	
	@Recover
	  public OrderModel Fallback(RuntimeException e,String code ) {
		System.out.println("service unavailable");
		OrderModel os = new OrderModel();
		os.setCode("service unavailable");
		os.setName("service unavailable");
		os.setStock((long) 0);
		return os;
	}
    
    /**
     * @param placeOrder
     * placeOrder method is used to place an order using product code and 
     * stock only when the required stock is available in inventory.
     * The stock is deducted when the order is placed.
     * Notification is send to the notification service once the order is placed.
     */
        
      
    public Boolean placeOrder(PlaceOrder placeOrder) {
    	String code = placeOrder.getCode();
    	// spring security for rest template
    	restTemplate.getInterceptors().add(new BasicAuthenticationInterceptor("rithu", "rithanya"));
    	OrderModel ispo = restTemplate.getForObject("http://localhost:6000/inventory/{code}", OrderModel.class,code);
    	if(ispo.getStock() >= placeOrder.getStock())
    	{
    		orderRepository2.save(placeOrder);
    		OrderModel object =new OrderModel();
    		object.setCode(placeOrder.getCode());
    		object.setStock(placeOrder.getStock());
    		
    		Long orderedstock = placeOrder.getStock();
    		Long inventorystock = ispo.getStock();
    		Long updatestock =inventorystock-orderedstock;
    		object.setCode(placeOrder.getCode());
    		object.setStock(updatestock);
    		restTemplate.put("http://localhost:6000/inventory/{code}", object, code);
    		kafkaTemplate.send("notificationItem",object);
    		return true; 
    	
    	}
    	else
    	{
    		return false;
    	}
    }
    
    
    

}
