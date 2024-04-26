package com.amiya.hoteratingapp.userService.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.amiya.hoteratingapp.userService.models.ResourceNotFoundExceptionResponse;

@RestControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<ResourceNotFoundExceptionResponse> resourceNotFoundExceptionHandler(ResourceNotFoundException ex){
		String msg = ex.getMessage();
		ResourceNotFoundExceptionResponse response = new ResourceNotFoundExceptionResponse();
		response.setMessage(msg);
		response.setStatus(HttpStatus.NOT_FOUND);
		response.setSuccess(true);
		return new ResponseEntity<ResourceNotFoundExceptionResponse>(response,HttpStatus.NOT_FOUND);
	}
}
