package com.main.entity;

import java.io.Serializable;
import java.util.Map;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.main.pojo.Range;
import com.mongodb.lang.NonNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/***
 * It stores all discount slabs details
 * 
 * @author snavale
 *
 */
@Document(collection = "discount_slabs")
@Data
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class DiscountSlabs implements Serializable{/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	private String id;

	@NonNull
	private String customerType;
	
	private Map<String,Range> amountRange;
	
	private String currency;
}
