package com.main.pojo;

import java.io.Serializable;
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
public class ProductRequestPojo implements Serializable {
	/**
	* 
	*/
	private static final long serialVersionUID = 1L;

	private String id;
	
	private Integer qty;

	private String customerType;

}
