package com.bluebook.restservices.bluebook.exceptions;

import java.util.Date;

// Custom Error details bean
public class CustomErrorDetails {

	private Date timestamp;
	private String errorDetails;
	private String message;
	
	public CustomErrorDetails(Date timestamp, String errorDetails, String message) {
		this.timestamp = timestamp;
		this.errorDetails = errorDetails;
		this.message = message;
	}
	
	public Date getTimestamp() {
		return timestamp;
	}
	
	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}
	
	public String getErrorDetails() {
		return errorDetails;
	}
	
	public void setErrorDetails(String errorDetails) {
		this.errorDetails = errorDetails;
	}
	
	public String getMessage() {
		return message;
	}
	
	public void setMessage(String message) {
		this.message = message;
	}
}
