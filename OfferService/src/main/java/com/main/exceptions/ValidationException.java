package com.main.exceptions;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ValidationException extends RuntimeException{
	
	private static final long serialVersionUID = 1L;

	private String message;
	
	private Object obj;
		
	public ValidationException(Object obj) {
		this.obj=obj;
	}
	
	public ValidationException(String message) {
		super(message);
		this.message = message;
	}
}
