package com.example.ps.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.ps.entity.Product;
import com.example.ps.service.ProductCN;

public interface ProductRepository extends JpaRepository<ProductCN, String> {

	Optional<ProductCN> findByCode(String code);
	

}
