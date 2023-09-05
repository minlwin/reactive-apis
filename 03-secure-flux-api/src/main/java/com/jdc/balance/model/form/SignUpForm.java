package com.jdc.balance.model.form;

import java.util.function.Function;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import com.jdc.balance.model.consts.Role;
import com.jdc.balance.model.entity.Account;
import com.jdc.balance.model.entity.SignInInfo;

import jakarta.validation.constraints.NotBlank;

public record SignUpForm(
		@NotBlank(message = "Please enter your name.")
		String name,
		@NotBlank(message = "Please enter login id.")
		String username,
		@NotBlank(message = "Please enter password.")
		String password
		) {
	
	public Account convert(Function<String, String> passwordEncoder) {
		return new Account(null, name, Role.Member, new SignInInfo(username, passwordEncoder.apply(password)), null);
	}
	
	public UsernamePasswordAuthenticationToken token() {
		return UsernamePasswordAuthenticationToken.unauthenticated(username, password);
	}
}
