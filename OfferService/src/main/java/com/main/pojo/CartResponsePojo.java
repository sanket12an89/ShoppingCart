package com.main.pojo;

import java.io.Serializable;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class CartResponsePojo implements Serializable {
	/**
	* 
	*/
	private static final long serialVersionUID = 1L;

	private List<ProductResponsePojo> productList;

	private String customerType;
	
	private String currency;

	private String discount;
	
	private Double totalPrice;
	
	private Double grantTotal;
}
