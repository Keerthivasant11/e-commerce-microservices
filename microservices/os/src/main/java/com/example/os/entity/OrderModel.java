package com.example.os.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class OrderModel {
	
	@Id
	private String name;
	private String code;
	private Long stock;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public Long getStock() {
		return stock;
	}
	public void setStock(Long stock) {
		this.stock = stock;
	}
	
	
	
	
	
}
