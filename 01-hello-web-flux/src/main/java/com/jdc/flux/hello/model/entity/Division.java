package com.jdc.flux.hello.model.entity;

import org.springframework.data.annotation.Id;

public record Division(
		@Id
		Integer id,
		String name,
		String region
		) {

	public Division withId(int id) {
		return new Division(id, name, region);
	}
}
