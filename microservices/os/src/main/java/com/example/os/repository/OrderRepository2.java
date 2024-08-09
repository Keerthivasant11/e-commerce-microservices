package com.example.os.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.os.entity.PlaceOrder;

public interface OrderRepository2 extends JpaRepository<PlaceOrder, String>{

}
