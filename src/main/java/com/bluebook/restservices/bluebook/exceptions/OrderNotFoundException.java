package com.bluebook.restservices.bluebook.exceptions;

public class OrderNotFoundException extends Exception{
	
	public static final long serialVersionUID=1L;
	
	public OrderNotFoundException(String message) {
		super(message);
	}
}
