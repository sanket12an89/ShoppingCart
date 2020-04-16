package com.main.service;

import java.util.List;
import java.util.Set;

import com.main.entity.DiscountSlabs;

public interface IOfferService {

	void loadDefaultDiscountSlabs(); 
	
	List<DiscountSlabs> getAll();
	
	Set<String> getCustomeTypeList();
	
	DiscountSlabs findByCustomerType(String customerType);
	
	void remove(String customerType);
	
	void removeAll();
	
	void add(DiscountSlabs discountSlabsObj);
}
