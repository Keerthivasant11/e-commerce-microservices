package com.example.os.repository;


import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import com.example.os.entity.OrderModel;

@DataJpaTest
public class TestOrderRepository {
	
	@Autowired
	private TestEntityManager entitymanager;
    
	@Autowired
	private OrderRepository orderRepository;
	
	@Test
	public void testfindbycode() {
		OrderModel jbo = nallamethod();
		OrderModel kiki = entitymanager.persist(jbo);
		
		OrderModel vas = orderRepository.findByCode(jbo.getCode());
		assertEquals(kiki,vas);
	}
	
	public OrderModel nallamethod() {
		OrderModel jbo = new OrderModel();
		jbo.setCode("manga");
		jbo.setName("bersek");
		jbo.setStock((long) 2); 
		return jbo;
		
	}
}
