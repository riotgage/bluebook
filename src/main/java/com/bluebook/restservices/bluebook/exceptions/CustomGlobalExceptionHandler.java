package com.bluebook.restservices.bluebook.exceptions;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;

@ControllerAdvice
public class CustomGlobalExceptionHandler extends ResponseEntityExceptionHandler {

	// MethodArgumentNotValidException
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
		
		CustomErrorDetails customErrorDetails=new CustomErrorDetails(new Date(),"Method Argument not valid in GEH",
				ex.getMessage());
		
		return new ResponseEntity<Object>(customErrorDetails,HttpStatus.BAD_REQUEST);
	}
	
	@Override
	protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		
		CustomErrorDetails customErrorDetails=new CustomErrorDetails(new Date(),"Method not supported in GEH",
				ex.getMessage());
		return new ResponseEntity<Object>(customErrorDetails,HttpStatus.BAD_REQUEST);
		
	}
	
	@ExceptionHandler(UserNameNotFoundException.class)
	public final ResponseEntity<Object> handleUserNameNotFoundException(UserNameNotFoundException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		CustomErrorDetails customErrorDetails=new CustomErrorDetails(new Date(),"UserName not found in User repository"
				,request.getDescription(false));
		return new ResponseEntity<Object>(customErrorDetails,HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(UserNotFoundException.class)
	public final ResponseEntity<Object> handleUserNameNotFoundException(UserNotFoundException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		CustomErrorDetails customErrorDetails=new CustomErrorDetails(new Date(),"User not found"
				,request.getDescription(false));
		return new ResponseEntity<Object>(customErrorDetails,HttpStatus.BAD_REQUEST);
		
	}
	
	@ExceptionHandler(ConstraintViolationException.class)
	public final ResponseEntity<Object> handleConstraintViolationExceptionn(ConstraintViolationException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		CustomErrorDetails customErrorDetails=new CustomErrorDetails(new Date(),"Constraint Violeted. "+ ex.getConstraintName()+" Must be valid."
				,request.getDescription(false));
		return new ResponseEntity<Object>(customErrorDetails,HttpStatus.BAD_REQUEST);
	}
}
