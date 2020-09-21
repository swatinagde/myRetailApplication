package com.casestudy.myRetail;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.skyscreamer.jsonassert.JSONAssert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;

import com.casestudy.myRetail.controller.ProductController;
import com.casestudy.myRetail.exception.ProductNotFoundException;
import com.casestudy.myRetail.helper.HelperUtil;
import com.casestudy.myRetail.model.CurrentPrice;
import com.casestudy.myRetail.model.Product;
import com.casestudy.myRetail.response.CurrentPriceResponseBean;
import com.casestudy.myRetail.response.ProductResponseBean;
import com.casestudy.myRetail.service.FetchProductNameService;
import com.casestudy.myRetail.service.ProductService;

@RunWith(SpringRunner.class)
@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = ProductController.class)
public class ProductControllerTest {

	Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	ProductService productService;

	@MockBean
	FetchProductNameService fetchProductNameService;

	@MockBean
	HelperUtil helperUtil;

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void checkHttpStatusGetProductByIdTest() throws Exception {
		String url = "/products/13860428";
		ProductResponseBean productResponseBean = new ProductResponseBean();
		CurrentPriceResponseBean currentPrice = new CurrentPriceResponseBean();
		productResponseBean.setProductId("13860428");
		productResponseBean.setName("The Big Lebowski (Blu-ray)");
		currentPrice.setCurrencyCode("USD");
		currentPrice.setValue(13.49);
		productResponseBean.setCurrentPrice(currentPrice);

		Product product = new Product();
		product.setProductId(productResponseBean.getProductId());
		CurrentPrice productCurrentPrice = new CurrentPrice();
		productCurrentPrice.setCurrencyCode(currentPrice.getCurrencyCode());
		productCurrentPrice.setValue(currentPrice.getValue());
		product.setCurrentPrice(productCurrentPrice);

		Mockito.when(productService.findProductById(Mockito.anyString())).thenReturn(product);
		MvcResult actual = mockMvc.perform(get("/products/13860428").accept(MediaType.APPLICATION_JSON)).andReturn();
		assertEquals("200", actual.getResponse().getStatus(), true);
	}

	@Test
	public void testProductNotPresent() throws Exception {
		try {
			String actual = mockMvc.perform(get("/products/999999")).andReturn().getResponse().getContentAsString();
			assertEquals("", actual);

		} catch (ProductNotFoundException e) {
			throw new ProductNotFoundException();
		}

	}
}
