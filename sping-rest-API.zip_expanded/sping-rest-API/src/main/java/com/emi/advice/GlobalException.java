package com.emi.advice;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.emi.dto.ErrorApi;
import com.emi.exception.ApplicationException;

//catch block for entire application
@RestControllerAdvice
public class GlobalException {
   
	@ExceptionHandler(ApplicationException.class)
	public ResponseEntity<ErrorApi> handleApplicationException(ApplicationException ex) {
		ErrorApi error=new ErrorApi("404" , ex.getMessage());
		return new ResponseEntity<ErrorApi>(error, org.springframework.http.HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Map<String,String>> handlesValidationException(MethodArgumentNotValidException ex){
		Map<String,String> error=new HashMap<>();
		ex.getFieldErrors().forEach(err -> error.put(err.getField(), err.getDefaultMessage()));
		return new ResponseEntity<Map<String,String>>(error, org.springframework.http.HttpStatus.BAD_REQUEST); 
	}
	
}
