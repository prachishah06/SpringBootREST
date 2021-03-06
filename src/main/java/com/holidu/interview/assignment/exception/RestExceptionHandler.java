package com.holidu.interview.assignment.exception;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.holidu.interview.assignment.entity.ErrorResponse;

@ControllerAdvice
public class RestExceptionHandler {
	
	@Autowired
	ErrorResponse errorResponse;

	@ExceptionHandler
	public ResponseEntity<ErrorResponse> studentNotFoundExceptionHandler(TreeNotFoundException snf) {

		// create StudentErrorResponse
		//StudentErrorResponse errorResponse=new StudentErrorResponse();
		
		errorResponse.setStatus(HttpStatus.NOT_FOUND.value());
		errorResponse.setMessage(snf.getMessage());
		errorResponse.setTimestamp(System.currentTimeMillis());

		// return ResponseEntity

		return new ResponseEntity<ErrorResponse>(errorResponse,HttpStatus.NOT_FOUND) ;
	}
	
	@ExceptionHandler
	public ResponseEntity<ErrorResponse> studentNotFoundExceptionHandler(Exception snf) {

		// create StudentErrorResponse
		//StudentErrorResponse errorResponse=new StudentErrorResponse();
		
		errorResponse.setStatus(HttpStatus.BAD_REQUEST.value());
		errorResponse.setMessage(snf.getMessage());
		errorResponse.setTimestamp(System.currentTimeMillis());

		// return ResponseEntity

		return new ResponseEntity<ErrorResponse>(errorResponse,HttpStatus.BAD_REQUEST) ;
	}
	
}
