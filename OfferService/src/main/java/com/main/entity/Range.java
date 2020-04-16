package com.main.entity;
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
public class Range implements Serializable {/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Long min;
	
	private Long max;
	
	private String clause;
}
