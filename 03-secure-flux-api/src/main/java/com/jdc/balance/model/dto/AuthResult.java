package com.jdc.balance.model.dto;

import java.util.List;

public record AuthResult(
		String username,
		List<String> authorities,
		String token
		) {

}
