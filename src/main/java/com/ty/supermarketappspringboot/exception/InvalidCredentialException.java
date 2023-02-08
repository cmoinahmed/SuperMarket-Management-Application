package com.ty.supermarketappspringboot.exception;

public class InvalidCredentialException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1200034191330476092L;
	
	private String message="Invalid Password";

	public InvalidCredentialException(String message) {
		this.message = message;
	}
	
	public InvalidCredentialException() {
		
	}
	
	@Override
	public String toString() {
		return message;
	}
}
