/*package com.example.os.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Optional;
import java.util.function.BooleanSupplier;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.web.client.RestTemplate;

import com.example.os.entity.OrderModel;
import com.example.os.entity.PlaceOrder;
import com.example.os.repository.OrderRepository;
import com.example.os.repository.OrderRepository2;

@SpringBootTest
public class TestOrderService {
 
	@InjectMocks
	private OrderService orderService;
	
	@Mock
	private OrderRepository orderRepository;
	
	@Mock
	private OrderRepository2 orderRepository2;
	
	@Mock
	private RestTemplate restTemplate;
	
	@Test
	public void testSaveProducts() {
		OrderModel om = saveproductmethod1();
		OrderModel expected = om;
		OrderModel om1 = saveproductmethod2();
		OrderModel om2 = saveproductmethod3();
		Mockito.when(restTemplate.getForObject("http://localhost:6000/Product/{code}", OrderModel.class,om1.getCode())).thenReturn(om1);
		Mockito.when(restTemplate.getForObject("http://localhost:6000/inventory/{code}", OrderModel.class,om2.getCode())).thenReturn(om2);
		Mockito.when(orderRepository.save(om)).thenReturn(om);
		Mockito.when(orderRepository.findByCode(om.getCode())).thenReturn(om);
        OrderModel actual = orderService.getProducts(om.getCode());
		assertEquals(expected,actual);
	}
	public OrderModel saveproductmethod1() {
		OrderModel om = new OrderModel();
		om.setCode("book");
		om.setName("atomic habits");
		om.setStock((long) 20);
		return om;
	}
	
	public OrderModel saveproductmethod2() {
		OrderModel om1 = new OrderModel();
		om1.setCode("book");
		om1.setName("atomic habits");
		return om1;
	}
	
	public OrderModel saveproductmethod3() {
		OrderModel om2 = new OrderModel();
		om2.setCode("book");
		om2.setStock((long) 20);
		return om2;
	}
	@Test
	public void testPlaceOrder() {
		PlaceOrder expected = placeOrder ();
		OrderModel om2 = saveproductmethod3();
		Mockito.when(restTemplate.getForObject("http://localhost:6000/inventory/{code}", OrderModel.class,expected.getCode())).thenReturn(om2);
		Boolean result = null;
		if(om2.getStock()>=expected.getStock()) {
			Mockito.when(orderRepository2.save(expected)).thenReturn(expected);
		    result= true;
		}
		
		assertTrue(result);
		
		
	}
	
	public PlaceOrder placeOrder() {
		PlaceOrder po = new PlaceOrder();
		po.setCode("book");
		po.setStock((long) 15);
		return po;
	}
	
	
}*/
