package com.jdc.balance.api;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jdc.balance.model.dto.AuthResult;

import reactor.core.publisher.Mono;

@RestController
@RequestMapping("security")
public class SecurityApi {

	@PostMapping("signin")
	Mono<AuthResult> signIn() {
		return null;
	}
	
	@PostMapping("signup")
	Mono<AuthResult> signUp() {
		return null;
	}
}
