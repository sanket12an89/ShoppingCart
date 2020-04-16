package com.main.helper.pojo;

import java.io.Serializable;

import com.main.constants.HTTPConstants;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Builder

/**
 * Gets the response.
 *
 * @return the response
 */
@Getter

/**
 * Sets the response.
 *
 * @param response
 *            the new response
 */
@Setter

/**
 * Instantiates a new generic response.
 */
@NoArgsConstructor

/**
 * Instantiates a new generic response.
 *
 */
@AllArgsConstructor

/*
 * (non-Javadoc)
 * 
 * @see java.lang.Object#toString()
 */
@ToString
public class ResponseData<T> implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	@Builder.Default
	private String status = HTTPConstants.MSG_SUUCESS;

	/** The custom messages. */
	private T message;

	/** The response data. */
	private T data;
}