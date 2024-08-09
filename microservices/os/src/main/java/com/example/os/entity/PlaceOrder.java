package com.example.os.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class PlaceOrder {
	
	@Id
	private String code;
	private Long stock;
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
