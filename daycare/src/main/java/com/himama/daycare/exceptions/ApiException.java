package com.himama.daycare.exceptions;

import java.util.HashMap;
import java.util.Map;

/**
 * 
 * @author nchopra
 *
 */
public class ApiException extends RuntimeException {

	private static final long serialVersionUID = -5617325434197351058L;

	private Map<String, Object> errors = new HashMap<String, Object>(0);

	public ApiException() {
		super();
	}

	public ApiException(String code, Object message) {
		super(code.concat(" : " + message));
		errors.put(code, message);
	}

	public ApiException(ExceptionCodes exception) {
		super(exception.getCode().concat(" : " + exception.getMessage()));
		errors.put(exception.getCode(), exception.getMessage());
	}

	public void addError(String code, String message) {
		errors.put(code, message);
	}

	public void addError(ExceptionCodes exception) {
		errors.put(exception.getCode(), exception.getMessage());
	}

	public Map<String, Object> getErrors() {
		return errors;
	}

	public void setErrors(Map<String, Object> errors) {
		this.errors = errors;
	}

}
