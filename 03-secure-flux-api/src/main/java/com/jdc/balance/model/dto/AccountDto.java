package com.jdc.balance.model.dto;

import com.jdc.balance.model.consts.Role;
import com.jdc.balance.model.entity.Account;

public record AccountDto(
		String id,
		String name,
		Role role,
		String email) {

	public static AccountDto from(Account doc) {
		return new AccountDto(doc.id(), doc.name(), doc.role(), doc.signIn().username());
	}
}
