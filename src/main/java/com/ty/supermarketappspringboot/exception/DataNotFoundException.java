package com.ty.supermarketappspringboot.exception;

public class DataNotFoundException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6757111930199881919L;

	private String message = "Data Not Present";

	public DataNotFoundException(String message) {
		this.message = message;
	}

	public DataNotFoundException() {

	}

	@Override
	public String toString() {
		return message;
	}
}
