package com.casestudy.myRetail;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.junit4.SpringRunner;

import com.casestudy.myRetail.model.CurrentPrice;
import com.casestudy.myRetail.model.Product;
import com.casestudy.myRetail.repository.ProductRepository;
import com.casestudy.myRetail.service.ProductService;

@RunWith(SpringRunner.class)
public class ProductServiceTest {

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
	}

	@Mock
	ProductService productService;

	@Mock
	ProductRepository productRepository;

	@Test
	public void getProductByIdTest() throws Exception {

		CurrentPrice currentPrice = new CurrentPrice(13.49, "USD");
		Product product = new Product("13860428", currentPrice);
		Mockito.when(productRepository.findProductByproductId(Mockito.anyString())).thenReturn(product);
		assertEquals("13860428", product.getProductId());
	}

}
