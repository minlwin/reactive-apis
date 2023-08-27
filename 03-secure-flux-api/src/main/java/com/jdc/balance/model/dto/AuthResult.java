package com.jdc.balance.model.dto;

import com.jdc.balance.model.consts.Role;

public record AuthResult(
		String username,
		Role role,
		String token
		) {

}
