package com.main.service.impl;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.main.constants.CommonEnum;
import com.main.constants.Constants;
import com.main.entity.DiscountSlabs;
import com.main.exceptions.GlobalExceptionHandler;
import com.main.pojo.CartResponsePojo;
import com.main.pojo.ProductRequestPojo;
import com.main.pojo.ProductResponsePojo;
import com.main.pojo.Range;
import com.main.service.ICartService;
import com.main.service.IOfferService;
import com.main.util.CommonUtils;

@Service
public class CartServiceImpl implements ICartService {

	private Logger LOGGER = LoggerFactory.getLogger(GlobalExceptionHandler.class);

	@Autowired
	IOfferService offerServiceObj;

	@Override
	public CartResponsePojo calcuate(String customerType, List<ProductResponsePojo> productList) {
		DiscountSlabs discountSlabs = offerServiceObj.findByCustomerType(customerType);

		CartResponsePojo cartPojo = CartResponsePojo.builder().build();
		Double total = 0.0;
		for (ProductResponsePojo productPojo : productList) {
			productPojo.setTotalPrice(productPojo.getPrice() * productPojo.getQty());
			total += productPojo.getTotalPrice();
		}
		cartPojo.setProductList(productList);
		cartPojo.setCurrency(discountSlabs.getCurrency());
		cartPojo.setCustomerType(customerType);
		cartPojo.setTotalPrice(total);
		cartPojo.setDiscount(calculateDiscount(total, customerType,discountSlabs));
		cartPojo.setGrantTotal(calculateTotal(total, cartPojo.getDiscount()));
		
		LOGGER.info("Calculate Grand Total {}"+cartPojo);
		return cartPojo;
	}

	private String calculateDiscount(Double total, String customerType,DiscountSlabs discountSlabs) {
		Map<String, Range> range = discountSlabs.getAmountRange();
		for (Map.Entry<String, Range> map : range.entrySet()) {
			Range rangeObj = map.getValue();

			if (rangeObj.getClause().equals(CommonEnum.Clause.BETWEEN.getLabel())) {
				if (total > rangeObj.getMin() && total < rangeObj.getMax()) {
					return map.getKey();
				}
			} else if (rangeObj.getClause().equals(CommonEnum.Clause.ABOVE.getLabel())) {
				if (total >= rangeObj.getMin()) {
					return map.getKey();
				}
			}
		}
		return Constants.EMPTY;
	}

	private Double calculateTotal(Double total, String discount) {
		if (discount.equals("NILL")) {
			return total;
		} else {
			double discountPercentage = Double.parseDouble(discount.replace("%", ""));
			double totalDiscountPrice = (total * discountPercentage) / 100;
			return (total - totalDiscountPrice);
		}
	}

	@Override
	public List<ProductResponsePojo> processProdcutList(ProductRequestPojo productRequestPojo) {
		LOGGER.info("ProductRequestPojo {}"+productRequestPojo);

		List<ProductResponsePojo> productList = CommonUtils.defaultProductData();
		for(ProductResponsePojo productObj : productList) {
			if(productRequestPojo.getId().equals(productObj.getId())) {
				System.out.println("MAtch");
				productObj.setQty(productRequestPojo.getQty());
			}	
		}
		
		LOGGER.info("ProductList {}"+productList);
		return productList;
	}

}
