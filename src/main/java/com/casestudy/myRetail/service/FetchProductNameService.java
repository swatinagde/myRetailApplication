package com.casestudy.myRetail.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.casestudy.myRetail.exception.ProductNotFoundException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class FetchProductNameService {

	private static final String APIENDPOINT = "https://redsky.target.com/v3/pdp/tcin/{id}"
			+ "?excludes=taxonomy,price,promotion,bulk_ship,rating_and_review_reviews,rating_and_review_statistics,question_answer_statistics&key=candidate";

	public FetchProductNameService() {

	}

	public String getProductName(String productId) throws JsonMappingException, JsonProcessingException {

		Map<String, String> uriVariables = new HashMap<>();
		JsonNode title;
		uriVariables.put("id", productId);
		try {
			ResponseEntity<String> responseEntity = new RestTemplate().getForEntity(APIENDPOINT, String.class,
					uriVariables);

			String response = responseEntity.getBody();

			ObjectMapper mapper = new ObjectMapper();
			JsonNode actualObj = mapper.readTree(response);

			title = actualObj.get("product").get("item").get("product_description").get("title");
		}catch(Exception e) {
			throw new ProductNotFoundException("Product Not Found in External API");
			
		}
		return title.textValue();
	}

}
