package com.ty.supermarketappspringboot.exception;

public class EmailIdNotFoundException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1335760193644105865L;

	private String message = "Invalid Email Id";

	public EmailIdNotFoundException(String message) {
		this.message = message;
	}

	public EmailIdNotFoundException() {

	}

	@Override
	public String getMessage() {
		return message;
	}
}