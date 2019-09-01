package com.himama.daycare.exceptions;

/**
 * 
 * @author nchopra
 *
 */
public enum ApiExCode implements ExceptionCodes {

	RECORD_NOT_FOUND("HM_404", "Record Not Found!"), NOT_VALID_RECORD("HM_303", "Not a valid record"), INTERNAL_SERVER_ERROR("HM_500","Internal Server Error");

	private String code;
	private String message;

	ApiExCode(String code, String message) {
		this.code = code;
		this.message = message;
	}

	@Override
	public String getCode() {
		return code;
	}

	@Override
	public String getMessage() {
		return message;
	}
}
