package com.casestudy.myRetail.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.casestudy.myRetail.model.Product;

public interface ProductRepository extends MongoRepository<Product, String> {
 
	/**
	 * @param productId
	 * @return
	 */
	public Product findProductByproductId(String productId);

}
