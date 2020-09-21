package com.casestudy.myRetail.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

public class CurrentPrice {
	private Double value;
	 private String currencyCode;
	
	public CurrentPrice(Double value,String currencyCode) {
		this.value = value;
		this.currencyCode = currencyCode;
	}

	public CurrentPrice() {
		// TODO Auto-generated constructor stub
	}

	public Double getValue() {
		return value;
	}

	public void setValue(Double value) {
		this.value = value;
	}

	public String getCurrencyCode() {
		return currencyCode;
	}

	public void setCurrencyCode(String currencyCode) {
		this.currencyCode = currencyCode;
	}
	

}
