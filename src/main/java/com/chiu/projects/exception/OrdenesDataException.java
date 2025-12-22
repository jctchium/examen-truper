package com.chiu.projects.exception;

public class OrdenesDataException extends Exception{
	private static final long serialVersionUID = -6735963953456714757L;
	
	private String message;
	
	public OrdenesDataException(String message) {
		this.message = message;
	}

	public String getMessage() {
		return this.message;
	}
}
