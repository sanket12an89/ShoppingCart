package com.main.exceptions;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.main.constants.HTTPConstants;
import com.main.helper.pojo.ResponseData;
import com.main.util.JsonSerializer;
/**
 * 
 * Handle all the global exceptions here
 * @author Sanket Navale
 *
 */
@ControllerAdvice
public class GlobalExceptionHandler {

	private Logger LOGGER = LoggerFactory.getLogger(GlobalExceptionHandler.class);

	/**
	 * Validation exception will catch here 
	 * @param request
	 * @param ex
	 * @return
	 * @throws IOException
	 */
	@ExceptionHandler(ValidationException.class)
	@ResponseBody
	public String handleValidationException(HttpServletRequest request, ValidationException ex) throws IOException {
		ResponseData<Object> responseDataObj = ResponseData.builder().build();
		responseDataObj.setMessage(ex.getMessage());
		responseDataObj.setStatus(HTTPConstants.MSG_VALIDATION);

		LOGGER.info("Validation Msg : " + responseDataObj);
		return JsonSerializer.serialize(responseDataObj);
	}

	/**
	 * Any possible exception will catch here, if not any of the above
	 * @param request
	 * @param ex
	 * @return
	 * @throws IOException
	 */
	@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler(Exception.class)
	@ResponseBody
	public String handleException(HttpServletRequest request, Exception ex) throws IOException {
		ResponseData<Object> responseDataObj = ResponseData.builder().build();
		responseDataObj.setMessage(ex.getMessage());
		responseDataObj.setStatus(HTTPConstants.MSG_ERROR);

		LOGGER.info("Exception Msg : " + responseDataObj);
		return JsonSerializer.serialize(responseDataObj);
	}

}
