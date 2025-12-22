package com.chiu.projects.globalhandler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.chiu.projects.exception.OrdenesDataException;
import com.chiu.projects.exception.ProductosDataException;

@ControllerAdvice
public class GlobalHandler {
	@ExceptionHandler(ProductosDataException.class) 
	public ResponseEntity<ErrorResponse> handleProductosDataException(ProductosDataException e) {
		ErrorResponse errorResponse = ErrorResponse.builder()
										.errorMessage(e.getMessage())
										.errorCode(HttpStatus.BAD_REQUEST.value())
										.build();
		return new ResponseEntity<ErrorResponse>(errorResponse, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(OrdenesDataException.class) 
	public ResponseEntity<ErrorResponse> handleOrdenesDataException(OrdenesDataException e) {
		ErrorResponse errorResponse = ErrorResponse.builder()
										.errorMessage(e.getMessage())
										.errorCode(HttpStatus.BAD_REQUEST.value())
										.build();
		return new ResponseEntity<ErrorResponse>(errorResponse, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(Exception.class) 
	public ResponseEntity<ErrorResponse> handleProductosDataException(Exception e) {
		ErrorResponse errorResponse = ErrorResponse.builder()
										.errorMessage(e.getMessage())
										.errorCode(HttpStatus.INTERNAL_SERVER_ERROR.value())
										.build();
		return new ResponseEntity<ErrorResponse>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
