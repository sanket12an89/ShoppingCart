package com.main.service;

import java.util.List;

import com.main.pojo.CartResponsePojo;
import com.main.pojo.ProductRequestPojo;
import com.main.pojo.ProductResponsePojo;

public interface ICartService {

	CartResponsePojo calcuate(String customerType,List<ProductResponsePojo> productList);

	List<ProductResponsePojo> processProdcutList(ProductRequestPojo productRequestPojo); 
}
