package com.jdc.balance.api;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jdc.balance.model.dto.AuthResult;
import com.jdc.balance.model.form.SignInForm;
import com.jdc.balance.model.form.SignUpForm;

import reactor.core.publisher.Mono;

@RestController
@RequestMapping("security")
public class SecurityApi {

	@PostMapping("signin")
	Mono<AuthResult> signIn(@Validated @RequestBody SignInForm form) {
		
		return null;
	}
	
	@PostMapping("signup")
	Mono<AuthResult> signUp(@Validated @RequestBody SignUpForm form) {
		
		
		
		return null;
	}
}
