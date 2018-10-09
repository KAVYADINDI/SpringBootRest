package com.customerapp.customerapp.controller;

import java.time.LocalDateTime;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.customerapp.customerapp.entity.ErrorMessage;
import com.customerapp.customerapp.exceptions.AuthenticationFailedException;
import com.customerapp.customerapp.exceptions.CustomerAlreadyRegisteredException;
import com.customerapp.customerapp.exceptions.CustomerNotFoundException;
@Controller
public class ExceptionController {

Logger log = LoggerFactory.getLogger(this.getClass());


    @ExceptionHandler(value = CustomerNotFoundException.class)
	public  ResponseEntity<ErrorMessage> customerNotFoundException(
			CustomerNotFoundException customerNotFoundException, HttpServletRequest request) {
		ErrorMessage errorMessage = new ErrorMessage(request.getRequestURI(), HttpStatus.NOT_FOUND,
				LocalDateTime.now(), customerNotFoundException.getMessage());
		log.error(errorMessage.toString());
		return new ResponseEntity<>(errorMessage, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(value = AuthenticationFailedException.class)
	public  ResponseEntity<ErrorMessage> authFailedException(
			AuthenticationFailedException customerNotFoundException, HttpServletRequest request) {
		ErrorMessage errorMessage = new ErrorMessage(request.getRequestURI(), HttpStatus.FORBIDDEN,
				LocalDateTime.now(), customerNotFoundException.getMessage());
		log.info(errorMessage.toString());
		return new ResponseEntity<>(errorMessage, HttpStatus.FORBIDDEN);
	}

	@ExceptionHandler(value = CustomerAlreadyRegisteredException.class)
	public  ResponseEntity<ErrorMessage> registrationFailedException(
			CustomerAlreadyRegisteredException customerNotFoundException, HttpServletRequest request) {
		ErrorMessage errorMessage = new ErrorMessage(request.getRequestURI(), HttpStatus.FOUND,
				LocalDateTime.now(), customerNotFoundException.getMessage());
		log.info(errorMessage.toString());
		return new ResponseEntity<ErrorMessage>(errorMessage, HttpStatus.FOUND);
	}
}
