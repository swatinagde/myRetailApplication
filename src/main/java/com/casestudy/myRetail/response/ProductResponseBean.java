package com.casestudy.myRetail.response;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown=true)
public class ProductResponseBean { 
	
	@JsonProperty(value="id")
	String productId;

	@JsonProperty(value="name")
	String name;

	@JsonProperty(value="current_price")
	CurrentPriceResponseBean currentPrice;

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}


	public CurrentPriceResponseBean getCurrentPrice() {
		return currentPrice;
	}

	public void setCurrentPrice(CurrentPriceResponseBean currentPrice) {
		this.currentPrice = currentPrice;
	}

	@Override
	public String toString() {
		return "ProductResponse [productId=" + productId + ", name=" + name + ", currentprice=" + currentPrice + "]";
	}

}
