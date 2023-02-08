package com.ty.supermarketappspringboot.exception;

public class InsufficientProductQuantity extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2308892006579068371L;

	private String message = "Insufficient Product Quantity Update the Product Quantity";

	public InsufficientProductQuantity(String message) {
		this.message = message;
	}

	public InsufficientProductQuantity() {

	}

	@Override
	public String getMessage() {
		return message;
	}

}
