package com.jdc.balance.model.form;

import java.util.function.Function;

import com.jdc.balance.model.consts.Role;
import com.jdc.balance.model.entity.Account;
import com.jdc.balance.model.entity.SignInInfo;

public record SignUpForm(
		String name,
		String username,
		String password
		) {
	
	public Account convert(Function<String, String> passwordEncoder) {
		return new Account(null, name, Role.Member, new SignInInfo(username, passwordEncoder.apply(password)), null);
	}
}
