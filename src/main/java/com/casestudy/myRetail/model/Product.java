package com.casestudy.myRetail.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.casestudy.myRetail.customBeanValidator.NumericString;


@Document(collection="productprice")
public class Product {
	public Product() {
	}

	@Id
	@NumericString(message = "productId should be numeric")
	private String productId;
	
	private CurrentPrice currentPrice;

	public Product(String productId, CurrentPrice currentPrice) {
		this.productId = productId;
		this.currentPrice = currentPrice;
	}
	
	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public CurrentPrice getCurrentPrice() {
		return currentPrice;
	}

	public void setCurrentPrice(CurrentPrice currentPrice) {
		this.currentPrice = currentPrice;
	}	
	
	

}
