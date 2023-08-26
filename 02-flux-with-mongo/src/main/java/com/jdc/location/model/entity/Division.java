package com.jdc.location.model.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;

public record Division(
		@Id
		String id,
		@Indexed(unique = true)
		String name, 
		String region
		) {

	public Division withId(String id) {
		return new Division(id, name, region);
	}
}
