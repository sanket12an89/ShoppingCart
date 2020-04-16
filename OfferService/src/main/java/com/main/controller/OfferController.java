package com.main.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.main.config.Messages;
import com.main.constants.HTTPConstants;
import com.main.entity.DiscountSlabs;
import com.main.exceptions.GlobalExceptionHandler;
import com.main.helper.pojo.ResponseData;
import com.main.service.IOfferService;

@RestController
@RequestMapping("/api/offer")
public class OfferController {

	private Logger LOGGER = LoggerFactory.getLogger(GlobalExceptionHandler.class);

	@Autowired
	IOfferService offerServiceObj;

	@Autowired
    Messages messages;
	
	/**
	 * Get all discount slabs with details
	 * 
	 * @return
	 */
	@GetMapping(value = "/")
	public ResponseData<Object> getDiscountSlabs() {
		ResponseData<Object> genericResponseObj = ResponseData.builder().build();
		genericResponseObj.setData(offerServiceObj.getAll());
		genericResponseObj.setStatus(HTTPConstants.MSG_SUUCESS);

		LOGGER.info("Get all discounts slabs details{} "+genericResponseObj);
		return genericResponseObj;
	}

	/**
	 * Get all discount slabs with details
	 * 
	 * @return
	 */
	@GetMapping(value = "/customerType/list")
	public ResponseData<Object> getCustomerTypeDropDown() {
		ResponseData<Object> genericResponseObj = ResponseData.builder().build();
		genericResponseObj.setData(offerServiceObj.getCustomeTypeList());
		genericResponseObj.setStatus(HTTPConstants.MSG_SUUCESS);

		LOGGER.info("Get all discounts slabs list {} "+genericResponseObj);
		return genericResponseObj;
	}

	/**
	 * Create new discount slabs
	 * 
	 * @param discountRequestObj
	 * @return
	 */
	@PostMapping(value = "/")
	public ResponseData<Object> addDiscountSlabs(@RequestBody DiscountSlabs discountRequestObj) {
		ResponseData<Object> genericResponseObj = ResponseData.builder().build();
		offerServiceObj.add(discountRequestObj);
		genericResponseObj.setData(messages.get("create.discount.msg"));
		genericResponseObj.setStatus(HTTPConstants.MSG_SUUCESS);

		LOGGER.info("Add discounts slabs {} "+genericResponseObj);
		return genericResponseObj;
	}

	/***
	 * Remove all discount slabs
	 * 
	 * @return
	 */
	@DeleteMapping(value = "/")
	public ResponseData<Object> removeAllDiscountSlabs() {
		ResponseData<Object> genericResponseObj = ResponseData.builder().build();
		offerServiceObj.removeAll();
		genericResponseObj.setData(messages.get("removeall.discount.msg"));
		genericResponseObj.setStatus(HTTPConstants.MSG_SUUCESS);

		LOGGER.info("Remove All discounts slabs {} "+genericResponseObj);
		return genericResponseObj;
	}

	/**
	 * Remove discount slabs by Customer Type
	 * 
	 * @param customerType
	 * @return
	 */
	@DeleteMapping(value = "/{customerType}")
	public ResponseData<Object> removeDiscountSlabs(@PathVariable("customerType") String customerType) {
		ResponseData<Object> genericResponseObj = ResponseData.builder().build();
		offerServiceObj.remove(customerType);
		genericResponseObj.setData(messages.get("remove.discount.msg"));
		genericResponseObj.setStatus(HTTPConstants.MSG_SUUCESS);
		
		
		LOGGER.info("Remove discounts slabs by name input{"+customerType+"} "+genericResponseObj);
		return genericResponseObj;
	}

}
