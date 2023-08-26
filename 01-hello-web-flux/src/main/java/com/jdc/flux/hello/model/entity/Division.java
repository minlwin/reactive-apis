package com.jdc.flux.hello.model.entity;

import org.springframework.data.annotation.Id;

import jakarta.validation.constraints.NotBlank;

public record Division(
		@Id
		Integer id,
		@NotBlank(message = "Please enter division name.")
		String name,
		@NotBlank(message = "Please enter region name.")
		String region
		) {

	public Division withId(int id) {
		return new Division(id, name, region);
	}
}
