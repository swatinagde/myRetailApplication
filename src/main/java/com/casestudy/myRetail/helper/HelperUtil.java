package com.casestudy.myRetail.helper;

import org.springframework.stereotype.Component;

import com.casestudy.myRetail.model.CurrentPrice;
import com.casestudy.myRetail.model.Product;
import com.casestudy.myRetail.response.CurrentPriceResponseBean;
import com.casestudy.myRetail.response.ProductResponseBean;

@Component
public class HelperUtil {

	public HelperUtil() {
		// TODO Auto-generated constructor stub
	}
	/**
	 * Create product response
	 * @param product
	 * @param productName
	 * @return
	 */
	public ProductResponseBean createProductResponse(Product product, String productName){

		ProductResponseBean prodResponseBean = new ProductResponseBean();
		CurrentPriceResponseBean currentPriceResponseBean = new CurrentPriceResponseBean();
			currentPriceResponseBean.setCurrencyCode(product.getCurrentPrice().getCurrencyCode());
			currentPriceResponseBean.setValue(product.getCurrentPrice().getValue());

			prodResponseBean.setProductId(product.getProductId());
			prodResponseBean.setCurrentPrice(currentPriceResponseBean);
			prodResponseBean.setName(productName);
		return prodResponseBean;
	}
	
	/**
	 * create product object 
	 * @param prodResponseBean
	 * @return
	 */
	public Product getProductModelObject(ProductResponseBean prodResponseBean) {
		Product product = new Product();
		CurrentPrice currentPrice = new CurrentPrice();
		product.setProductId(prodResponseBean.getProductId());
		currentPrice.setCurrencyCode(prodResponseBean.getCurrentPrice().getCurrencyCode());
		currentPrice.setValue(prodResponseBean.getCurrentPrice().getValue());
		product.setCurrentPrice(currentPrice);
		return product;
	}

}
