package com.jdc.flux.hello.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.bind.support.WebExchangeBindException;

import com.jdc.flux.hello.model.dto.ErrorResult;
import com.jdc.flux.hello.model.dto.ErrorResult.Type;

import reactor.core.publisher.Mono;

@RestControllerAdvice
public class ExceptionHandlers {

	@ExceptionHandler
	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	public Mono<ErrorResult> handle(WebExchangeBindException e) {
		return Mono.just(new ErrorResult(Type.Validation, e.getFieldErrors().stream().map(a -> a.getDefaultMessage()).toList()));
	}
}
