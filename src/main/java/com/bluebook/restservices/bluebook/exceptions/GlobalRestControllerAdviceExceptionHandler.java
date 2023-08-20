package com.bluebook.restservices.bluebook.exceptions;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.util.Date;

@RestControllerAdvice
public class GlobalRestControllerAdviceExceptionHandler {
	
	@ExceptionHandler(UserNameNotFoundException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public final CustomErrorDetails handleUserNameNotFoundException(UserNameNotFoundException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		CustomErrorDetails customErrorDetails=new CustomErrorDetails(new Date(),"UserName not found in User repository"
				,request.getDescription(false));
		return customErrorDetails;
	}
	
}
