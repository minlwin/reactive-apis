package com.jdc.balance.model.entity;

import org.springframework.data.mongodb.core.index.Indexed;

public record SignInInfo(
		@Indexed(unique = true)
		String username,
		String password
		) {

}
