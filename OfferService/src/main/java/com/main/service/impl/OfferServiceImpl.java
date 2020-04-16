package com.main.service.impl;

import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

import javax.validation.ValidationException;

import org.apache.commons.lang3.ObjectUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.main.config.Messages;
import com.main.constants.Constants;
import com.main.entity.DiscountSlabs;
import com.main.repository.IOfferRepsitory;
import com.main.service.IOfferService;
import com.main.util.CommonUtils;

@Service
public class OfferServiceImpl implements IOfferService, InitializingBean {

	private Logger LOGGER = LoggerFactory.getLogger(this.getClass());

	@Autowired
	IOfferRepsitory offerRepoObj;

	@Autowired
	Messages messages;
	
	
	@Override
	public void afterPropertiesSet() throws Exception {
		loadDefaultDiscountSlabs();
	}

	@Override
	public void loadDefaultDiscountSlabs() {

		List<DiscountSlabs> existdiscountList = getAll();
		boolean existAccountSlabsObj = existdiscountList.stream().anyMatch(s -> s.getCustomerType().contains(Constants.REGULAR) || s.getCustomerType().contains(Constants.PREMIUM)); 
		if (!existAccountSlabsObj) {
			List<DiscountSlabs> discountList = CommonUtils.defaultDiscountSlabs();
			LOGGER.info("Load default discount slabs :" + discountList);
			offerRepoObj.saveAll(discountList);
		}
	}

	@Override
	public List<DiscountSlabs> getAll() {
		return offerRepoObj.findAll();
	}

	@Override
	public DiscountSlabs findByCustomerType(String customerType) {
		return offerRepoObj.findByCustomerType(customerType);
	}

	@Override
	public void remove(String customerType) {
		DiscountSlabs discountSlabObj = offerRepoObj.findByCustomerType(customerType);
		if (ObjectUtils.isEmpty(discountSlabObj)) {
			throw new ValidationException((messages.get("customer.not.msg")));
		}
		offerRepoObj.delete(discountSlabObj);
	}

	@Override
	public void removeAll() {
		offerRepoObj.deleteAll(); // remove all discount slabs
	}

	@Override
	public void add(DiscountSlabs discountSlabsObj) {

		DiscountSlabs discountSlabExistObj = offerRepoObj.findByCustomerType(discountSlabsObj.getCustomerType());
		if (!ObjectUtils.isEmpty(discountSlabExistObj)) {
			throw new ValidationException(messages.get("customer.exist.msg"));
		}
		discountSlabsObj.setId(UUID.randomUUID().toString());
		offerRepoObj.save(discountSlabsObj);
	}

	@Override
	public Set<String> getCustomeTypeList() {
		return getAll().stream().map(x -> x.getCustomerType()).collect(Collectors.toSet());
	}

}
