package com.jdc.balance.model.entity;

import java.util.Optional;

import org.springframework.data.annotation.Id;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.jdc.balance.model.consts.Role;
import com.jdc.balance.model.form.SignUpForm;

public record Account(
		@Id
		String id,
		String name,
		Role role,
		SignInInfo signIn,
		Optional<AuditInfo> audit
		) {
	
	public Account withRole(Role role) {
		return new Account(id, name, role, signIn, audit);
	}
	
	public static Account from(SignUpForm form, PasswordEncoder encoder) {
		return new Account(null, form.name(), null, new SignInInfo(form.username(), encoder.encode(form.password())), null);
	}
}
