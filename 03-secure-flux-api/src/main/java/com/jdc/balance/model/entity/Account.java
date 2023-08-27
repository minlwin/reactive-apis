package com.jdc.balance.model.entity;

import java.util.Optional;

import org.springframework.data.annotation.Id;

import com.jdc.balance.model.consts.Role;

public record Account(
		@Id
		String id,
		String name,
		Role role,
		SignInInfo signIn,
		Optional<AuditInfo> audit
		) {

}
