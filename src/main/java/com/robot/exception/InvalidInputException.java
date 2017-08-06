package com.robot.exception;

/**
 * Customised exception which will be thrown when input is invalid.
 */
public class InvalidInputException extends Exception {
	private static final long serialVersionUID = 1L;
	private String errorMessage;
 
	public String getErrorMessage() {
		return errorMessage;
	}
	
	public InvalidInputException(String errorMessage) {
		super(errorMessage);
		this.errorMessage = errorMessage;
	}
	
	public InvalidInputException() {
		super();
	}
}
