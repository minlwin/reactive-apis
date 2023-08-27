package com.jdc.balance.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.bind.support.WebExchangeBindException;

import com.jdc.balance.model.consts.ErrorType;
import com.jdc.balance.model.dto.ErrorResponse;

import reactor.core.publisher.Mono;

@RestControllerAdvice
public class ValidationExceptionsHandler {
	
	@ExceptionHandler
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public Mono<ErrorResponse> onValidationError(WebExchangeBindException e) {
		return Mono.just(new ErrorResponse(ErrorType.Validation, 
				e.getFieldErrors().stream().map(a -> a.getDefaultMessage()).toList()));
	}
}
