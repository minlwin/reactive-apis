package com.jdc.location.model.entity;

import org.springframework.data.annotation.Id;

public record Division(
		@Id
		String id,
		String name, 
		String region
		) {

	public Division withId(String id) {
		return new Division(id, name, region);
	}
}
