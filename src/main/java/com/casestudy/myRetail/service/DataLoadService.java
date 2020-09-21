package com.casestudy.myRetail.service;


import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.casestudy.myRetail.model.CurrentPrice;
import com.casestudy.myRetail.model.Product;
import com.casestudy.myRetail.repository.ProductRepository;


@Service
public class DataLoadService {

	@Autowired
	private ProductRepository productRepository;

	public DataLoadService() {};

	@PostConstruct
	public void init() {
		loadProductPrice();
	}

/**
 * Load product price in DB
 */
	private void loadProductPrice() {


		if(productRepository != null) {

			List<Product> prodList = new ArrayList<Product>();
			CurrentPrice currentPrice1 = new CurrentPrice();
			 currentPrice1.setCurrencyCode("USD");
			currentPrice1.setValue(13.49);
			Product product1 = new Product("13860428",currentPrice1);
			prodList.add(product1);

			CurrentPrice currentPrice2 = new CurrentPrice();
			currentPrice2.setCurrencyCode("USD");
			currentPrice2.setValue(18.99);
			Product product2 = new Product("16752456",currentPrice2);
			prodList.add(product2);

			CurrentPrice currentPrice3 = new CurrentPrice();
			currentPrice3.setCurrencyCode("USD");
			currentPrice3.setValue(29.99);
			Product product3 = new Product("16752457",currentPrice2);
			prodList.add(product3);

	
			productRepository.deleteAll();

			productRepository.saveAll(prodList);
		}
	}

}