package com.capgemini.orderapp.service.impl;

public class OrderDoesnotExistsException extends RuntimeException {

	public OrderDoesnotExistsException(String message) {
		super(message);
		
	}

}
