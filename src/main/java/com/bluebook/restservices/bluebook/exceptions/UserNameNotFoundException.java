package com.bluebook.restservices.bluebook.exceptions;

public class UserNameNotFoundException extends Exception{
	
	public static final long serialVersionUID=1L;
	
	public UserNameNotFoundException(String message) {
		super(message);
	}
}
