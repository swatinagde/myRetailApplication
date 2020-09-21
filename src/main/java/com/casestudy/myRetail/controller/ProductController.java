package com.casestudy.myRetail.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.casestudy.myRetail.customBeanValidator.NumericString;
import com.casestudy.myRetail.exception.ExceptionResponse;
import com.casestudy.myRetail.exception.ProductNotFoundException;
import com.casestudy.myRetail.helper.HelperUtil;
import com.casestudy.myRetail.model.Product;
import com.casestudy.myRetail.response.ProductResponseBean;
import com.casestudy.myRetail.service.FetchProductNameService;
import com.casestudy.myRetail.service.ProductService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;

@RestController
@Validated
public class ProductController {
	
	@Autowired
	private ProductService productService;
	
	@Autowired
	private FetchProductNameService fetchProductNameService;
	
	@Autowired
	private HelperUtil helperUtil;

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@GetMapping(path = "/products/{id}")
	public ResponseEntity<ProductResponseBean> getProductById(@PathVariable("id") @NumericString(message = "productId should be numeric") String productId)
			throws JsonMappingException, JsonProcessingException {
		Product product = productService.findProductById(productId);
		String productName = fetchProductNameService.getProductName(productId);
		ProductResponseBean prodResponseBean = new ProductResponseBean();
		prodResponseBean = helperUtil.createProductResponse(product, productName);
		logger.debug(" Product Response " + prodResponseBean);
		return new ResponseEntity<ProductResponseBean>(prodResponseBean, HttpStatus.OK);
	}
	
	@PutMapping(path = "/products/{id}")
	public ResponseEntity<ProductResponseBean> updateProduct(@RequestBody ProductResponseBean product,
			@PathVariable("id") @NumericString(message = "productId should be numeric") String productId) throws ProductNotFoundException {
		if (null!=product && !product.getProductId().equalsIgnoreCase(productId)) {
			throw new ProductNotFoundException("Product Id in URL and JSON mismatch");
		}
		Product productModelObject = helperUtil.getProductModelObject(product);
		productService.updateProductById(productModelObject);
		return new ResponseEntity<ProductResponseBean>(HttpStatus.OK);
	}
	
}
