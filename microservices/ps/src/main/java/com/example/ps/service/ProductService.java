package com.example.ps.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.example.ps.entity.Product;
import com.example.ps.repo.ProductRepository;

@Component
@Service
public class ProductService {
	@Autowired
	private ProductRepository productrepository;
	
	@Autowired
	private KafkaTemplate<String, ProductCS> kafkaTemplate;

	/**
	 * @param product
	 * createProducts method is used to save product name and code in product serviced.
	 * product code and stock are saved in inventory service using kafka.
	 */
	public ProductCN createProducts(Product product) {
		ProductCN productCN =new ProductCN();
		productCN.setCode(product.getCode());
		productCN.setName(product.getName());
		
		ProductCS productCS = new ProductCS();
		productCS.setCode(product.getCode());
		productCS.setStock(product.getStock());
		kafkaTemplate.send("StockDetails",productCS);
		return productrepository.save(productCN);
	}

	public Optional<ProductCN> getProducts(String code) {
		
		
		return productrepository.findByCode(code);
	}

}
