package com.bluebook.restservices.bluebook.exceptions;

public class UserExistsException extends Exception {
	
	public static final long serialVersionUID=1L;
	
	public UserExistsException(String message) {
		super(message);
	}
}
