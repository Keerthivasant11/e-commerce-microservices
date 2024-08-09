package com.example.invservice.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.invservice.entity.ProductCS;

public interface InventoryRepository extends JpaRepository<ProductCS, String>{

	ProductCS findByCode(String code);

}
