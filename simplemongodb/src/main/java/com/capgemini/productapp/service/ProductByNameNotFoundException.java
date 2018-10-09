package com.capgemini.productapp.service;

public class ProductByNameNotFoundException extends RuntimeException {

	public ProductByNameNotFoundException(String message) {
		super(message);
	
	}

}
