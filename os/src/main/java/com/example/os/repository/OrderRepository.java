package com.example.os.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import com.example.os.entity.OrderModel;

public interface OrderRepository extends CrudRepository<OrderModel,String>{

	OrderModel findByCode(String code);

	

	
}
