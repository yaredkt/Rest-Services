package com.example.demo.Exception;

import java.util.Date;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.example.demo.commons.RandomString;
import com.example.demo.user.BadUserRequestException;
import com.example.demo.user.UserNotFoundException;


@ControllerAdvice
@RestController
public class CustomizedResponseEntityExceptionHandler  extends ResponseEntityExceptionHandler {
	
	public static  final int n = 16;
	
	@ExceptionHandler({Exception.class,  NullPointerException.class}) 
	public final ResponseEntity<Object> handleAllException(ExceptionResponse ex, WebRequest request)
	{
		
		ExceptionResponse exceptionResponse = new ExceptionResponse(RandomString.getAlphaNumericString(n) ,  new Date(), ex.getMessage(), "request.getDescription(false)");
		
		return new ResponseEntity<>(exceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ExceptionHandler(UserNotFoundException.class)
	public final ResponseEntity<Object> handleUserNotFoundException(UserNotFoundException ex, WebRequest request) {
		ExceptionResponse exceptionResponse = new ExceptionResponse(RandomString.getAlphaNumericString(n), new Date(), ex.getMessage(),
				request.getDescription(false));
		return new ResponseEntity(exceptionResponse, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(BadUserRequestException.class)
	public final ResponseEntity<Object> handleBadRequestException(BadUserRequestException ex, WebRequest request) {
		ExceptionResponse exceptionResponse = new ExceptionResponse(RandomString.getAlphaNumericString(n), new Date(), ex.getMessage(),
				request.getDescription(false));
		return new ResponseEntity(exceptionResponse, HttpStatus.BAD_REQUEST);
	}
	
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		ExceptionResponse exceptionResponse = new ExceptionResponse(RandomString.getAlphaNumericString(n), new Date(), "Validation Failed", ex.getBindingResult().toString());
		return new ResponseEntity(exceptionResponse, HttpStatus.BAD_REQUEST);
	}

}
