package com.jdc.location.model.entity;

import org.springframework.data.annotation.Id;

public record Township(
		@Id
		String id,
		String name,
		String division
		) {
	
	public Township withId(String id) {
		return new Township(id, name, division);
	}
}
