package com.capgemini.orderapp.service.exceptions;

public class OrderExistsException extends RuntimeException {

	public OrderExistsException(String message) {
		super(message);
		
	}

}
