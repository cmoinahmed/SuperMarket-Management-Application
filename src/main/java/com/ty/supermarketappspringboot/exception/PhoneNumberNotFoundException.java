package com.ty.supermarketappspringboot.exception;

public class PhoneNumberNotFoundException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7356649599438842762L;
	
	private String message = "Phone Number Does Not Exist";

	public PhoneNumberNotFoundException(String message) {
		this.message = message;
	}

	public PhoneNumberNotFoundException() {
	}

	@Override
	public String getMessage() {
		return message;
	}
}

