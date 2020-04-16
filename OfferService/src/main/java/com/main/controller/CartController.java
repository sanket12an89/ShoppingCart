package com.main.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.main.constants.HTTPConstants;
import com.main.exceptions.GlobalExceptionHandler;
import com.main.helper.pojo.ResponseData;
import com.main.pojo.ProductRequestPojo;
import com.main.pojo.ProductResponsePojo;
import com.main.service.ICartService;
import com.main.util.CommonUtils;

@RestController
@RequestMapping("/api/cart")
public class CartController {

	private Logger LOGGER = LoggerFactory.getLogger(GlobalExceptionHandler.class);

	@Autowired
	ICartService cartServiceObj;

	/**
	 * Calculate cart details
	 * 
	 * @return
	 */
	@GetMapping(value = "/loadProduct/{customerType}")
	public ResponseData<Object> loadDefaulProductList(@PathVariable("customerType") String customerType) {
		ResponseData<Object> genericResponseObj = ResponseData.builder().build();
		genericResponseObj.setData(cartServiceObj.calcuate(customerType, CommonUtils.defaultProductData()));
		genericResponseObj.setStatus(HTTPConstants.MSG_SUUCESS);
		
		LOGGER.info("load products {} "+genericResponseObj);
		return genericResponseObj;
	}

	/**
	 * Calculate cart details
	 * 
	 * @return
	 */
	@PostMapping(value = "/loadProductByQty")
	public ResponseData<Object> loadProductListByQTY(@RequestBody ProductRequestPojo productRequestPojo) {
		ResponseData<Object> genericResponseObj = ResponseData.builder().build();
		
		List<ProductResponsePojo> productList = cartServiceObj.processProdcutList(productRequestPojo);	
		genericResponseObj.setData(cartServiceObj.calcuate(productRequestPojo.getCustomerType(), productList));
		genericResponseObj.setStatus(HTTPConstants.MSG_SUUCESS);
		
		LOGGER.info("load products based on qty change{} "+genericResponseObj);
		return genericResponseObj;
	}
}
