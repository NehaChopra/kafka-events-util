package com.quintifi.kafkaeventsutil.exceptions;

/**
 * 
 * @author nchopra
 *
 */
public enum ApiExCode implements ExceptionCodes {

	INTERNAL_SERVER_ERROR("GL_500", "Internal Server Error"),;

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
