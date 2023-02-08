package com.ty.supermarketappspringboot.exception;

public class IdNotFoundException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3400192602917691824L;
	
	private String message = "Id Not Found";

	public IdNotFoundException(String message) {
		this.message = message;
	}

	public IdNotFoundException() {
	}

	@Override
	public String getMessage() {
		return message;
	}
}
