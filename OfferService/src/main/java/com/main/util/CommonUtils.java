package com.main.util;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.main.constants.CommonEnum.Clause;
import com.main.constants.Constants;
import com.main.entity.DiscountSlabs;
import com.main.pojo.ProductResponsePojo;
import com.main.pojo.Range;

public class CommonUtils {

	public static List<ProductResponsePojo> defaultProductData() {

		List<ProductResponsePojo> productList = new ArrayList<>();
		ProductResponsePojo product1 = ProductResponsePojo.builder().build();
		product1.setId("PR_c4c95080-fb8c-49f8-a80e-b070c0e312c6");
		product1.setName("Samsung M1");
		product1.setQty(2);
		product1.setPrice(Double.valueOf(1000));

		ProductResponsePojo product2 = ProductResponsePojo.builder().build();
		product2.setId("PR_89400d98-dfd6-47cd-9ec1-dd980c453271");
		product2.setName("Apple Watch");
		product2.setQty(1);
		product2.setPrice(Double.valueOf(1200));

		productList.add(product1);
		productList.add(product2);
		return productList;

	}

	public static List<DiscountSlabs> defaultDiscountSlabs() {
		List<DiscountSlabs> discountslabsList = new ArrayList<>();

		DiscountSlabs regular = DiscountSlabs.builder().build();
		regular.setCustomerType(Constants.REGULAR);
		regular.setCurrency(Constants.CURRENCY);

		Map<String, Range> rangeRegularMap = new LinkedHashMap<>();
		Range regularRange1 = Range.builder().build();
		regularRange1.setMin(Long.valueOf(0));
		regularRange1.setMax(Long.valueOf(5000));
		regularRange1.setClause(Clause.BETWEEN.getLabel());
		rangeRegularMap.put("NILL", regularRange1);

		Range regularRange2 = Range.builder().build();
		regularRange2.setMin(Long.valueOf(5000));
		regularRange2.setMax(Long.valueOf(10000));
		regularRange2.setClause(Clause.BETWEEN.getLabel());
		rangeRegularMap.put("10%", regularRange2);

		Range regularRange3 = Range.builder().build();
		regularRange3.setMin(Long.valueOf(10000));
		regularRange3.setMax(Long.valueOf(10000));
		regularRange3.setClause(Clause.ABOVE.getLabel());
		rangeRegularMap.put("20%", regularRange3);

		regular.setAmountRange(rangeRegularMap);

		DiscountSlabs premium = DiscountSlabs.builder().build();
		premium.setCustomerType(Constants.PREMIUM);
		premium.setCurrency(Constants.CURRENCY);
		Map<String, Range> rangePremiumMap = new LinkedHashMap<>();

		Range premiumRange1 = Range.builder().build();
		premiumRange1.setMin(Long.valueOf(0));
		premiumRange1.setMax(Long.valueOf(4000));
		premiumRange1.setClause(Clause.BETWEEN.getLabel());
		rangePremiumMap.put("10%", premiumRange1);

		Range premiumRange2 = Range.builder().build();
		premiumRange2.setMin(Long.valueOf(4000));
		premiumRange2.setMax(Long.valueOf(8000));
		premiumRange2.setClause(Clause.BETWEEN.getLabel());
		rangePremiumMap.put("15%", premiumRange2);

		Range premiumRange3 = Range.builder().build();
		premiumRange3.setMin(Long.valueOf(8000));
		premiumRange3.setMax(Long.valueOf(12000));
		premiumRange3.setClause(Clause.BETWEEN.getLabel());
		rangePremiumMap.put("20%", premiumRange3);

		Range premiumRange4 = Range.builder().build();
		premiumRange4.setMin(Long.valueOf(12000));
		premiumRange4.setMax(Long.valueOf(12000));
		premiumRange4.setClause(Clause.ABOVE.getLabel());
		rangePremiumMap.put("30%", premiumRange4);

		premium.setAmountRange(rangePremiumMap);

		discountslabsList.add(regular);
		discountslabsList.add(premium);

		return discountslabsList;
	}
}
