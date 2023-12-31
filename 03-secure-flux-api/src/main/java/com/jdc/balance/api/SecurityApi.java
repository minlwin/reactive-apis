package com.jdc.balance.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jdc.balance.model.dto.AuthResult;
import com.jdc.balance.model.form.SignInForm;
import com.jdc.balance.model.form.SignUpForm;
import com.jdc.balance.model.service.AccountService;
import com.jdc.balance.security.JwtTokenProvider;

import reactor.core.publisher.Mono;

@RestController
@RequestMapping("security")
public class SecurityApi {
	
	@Autowired
	private ReactiveAuthenticationManager authenticationManager;
	@Autowired
	private JwtTokenProvider jwtTokenProvider;
	@Autowired
	private AccountService service;

	@PostMapping("signin")
	Mono<AuthResult> signIn(@Validated @RequestBody SignInForm form) {
		return authenticationManager.authenticate(form.authentication())
				.filter(auth -> null != auth 
					&& !(auth instanceof AnonymousAuthenticationToken) 
					&& auth.isAuthenticated())
				.map(auth -> new AuthResult(
						auth.getName(), 
						auth.getAuthorities().stream().map(a -> a.getAuthority()).toList(), 
						jwtTokenProvider.generate(auth)));
	}
	
	@PostMapping("signup")
	Mono<AuthResult> signUp(@Validated @RequestBody SignUpForm form) {
		return service.signUpAccount(form)
				.map(account -> form.token())
				.flatMap(token -> authenticationManager.authenticate(token))
				.filter(auth -> null != auth 
					&& !(auth instanceof AnonymousAuthenticationToken) 
					&& auth.isAuthenticated())
				.map(auth -> new AuthResult(
						auth.getName(), 
						auth.getAuthorities().stream().map(a -> a.getAuthority()).toList(), 
						jwtTokenProvider.generate(auth)));
	}
}
