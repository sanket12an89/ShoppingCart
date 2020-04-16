package com.main.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.main.entity.DiscountSlabs;

public interface IOfferRepsitory extends MongoRepository<DiscountSlabs, String> {

	DiscountSlabs findByCustomerType(String customerType);
	
}
