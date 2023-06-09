package com.samta.ai.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalException {

	
	@ExceptionHandler(QuestionException.class)
	public ResponseEntity<String> QuestionExceptionHandler(QuestionException questionException){
		
		return new ResponseEntity<String>(questionException.getMessage(),HttpStatus.BAD_REQUEST);
	}
}
