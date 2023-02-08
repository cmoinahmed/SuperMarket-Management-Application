package com.ty.supermarketappspringboot.exception;

public class InvalidStockTypeException extends RuntimeException {


	/**
	 * 
	 */
	private static final long serialVersionUID = 6729907174235710312L;
	

	String message = "Invalid Type";

	@Override
	public String getMessage() {

		return message;

	}

	public InvalidStockTypeException(String message) {

		this.message = message;
	}

	public InvalidStockTypeException() {

	}

}
