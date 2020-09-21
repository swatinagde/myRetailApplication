package com.casestudy.myRetail.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.casestudy.myRetail.exception.ProductNotFoundException;
import com.casestudy.myRetail.model.Product;
import com.casestudy.myRetail.repository.ProductRepository;
import com.casestudy.myRetail.response.ProductResponseBean;

@Service
public class ProductService {
	@Autowired
	private ProductRepository productRepository;
	
	public Product findProductById(String productId) {
		Product product = new Product();
	
		product = productRepository.findProductByproductId(productId);
		if(product== null) {
			throw new ProductNotFoundException("Product Not Found in Database");
		}
		return product;
	}

	public void updateProductById(Product product) {
		if(null!= product) {
			productRepository.save(product);
		}
		
	}

}
