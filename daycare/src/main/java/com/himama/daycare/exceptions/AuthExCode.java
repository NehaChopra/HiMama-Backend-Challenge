package com.himama.daycare.exceptions;

/**
 * 
 * @author nchopra
 *
 */
public enum AuthExCode implements ExceptionCodes {

	USER_TOKEN_INVALID("AU_501", "Invalid User Token"), TOKEN_EXPIRED("AU_502", "SSO Token Expired"),
	USER_DIASABLED("AU_503", "User is disabled!"), BAD_CREDDNETIALS("AU_504", "Bad credentials!"),
	EMAIL_NOT_FOUND("AU_505","Email not found!");

	private String code;
	private String message;

	AuthExCode(String code, String message) {
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
