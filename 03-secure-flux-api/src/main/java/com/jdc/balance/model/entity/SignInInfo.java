package com.jdc.balance.model.entity;

import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

public record SignInInfo(
		@Indexed(unique = true)
		String username,
		String password
		) {

	public UsernamePasswordAuthenticationToken token() {
		return UsernamePasswordAuthenticationToken.unauthenticated(username, password);
	}
}
