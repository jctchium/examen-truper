package com.chiu.projects.exception;

public class ProductosDataException extends Exception{
	private static final long serialVersionUID = 4475634678714508586L;

	private String message;
	
	public ProductosDataException(String message) {
		this.message = message;
	}
	
	public String getMessage() {
		return this.message;
	}
}
