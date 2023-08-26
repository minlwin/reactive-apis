package com.jdc.flux.hello.model.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record Township(
		@Id
		Integer id,
		@NotBlank(message = "Please enter township name.")
		String name,
		@NotNull(message = "Please select division")
		@Column("division_id")
		Integer divisionId
		) {

	public Township withId(int id) {
		return new Township(id, name, divisionId);
	}
}
