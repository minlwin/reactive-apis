package com.jdc.balance.model.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.jdc.balance.model.consts.Role;
import com.jdc.balance.model.entity.audit.AuditInfo;
import com.jdc.balance.model.entity.audit.AuditableDocument;
import com.jdc.balance.model.form.SignUpForm;

@Document
public record Account(
		@Id
		String id,
		String name,
		Role role,
		SignInInfo signIn,
		AuditInfo audit
		) implements AuditableDocument{
	
	public Account(String name, Role role, SignInInfo signIn) {
		this(null, name, role, signIn, new AuditInfo());
	}
	
	public Account withRole(Role role) {
		return new Account(id, name, role, signIn, audit);
	}
	
	public static Account from(SignUpForm form, PasswordEncoder encoder) {
		return new Account(form.name(), null, new SignInInfo(form.username(), encoder.encode(form.password())));
	}
}
