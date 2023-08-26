package com.jdc.flux.hello.model.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;

public record Township(
		@Id
		Integer id,
		String name,
		@Column("division_id")
		int divisionId
		) {

	public Township withId(int id) {
		return new Township(id, name, divisionId);
	}
}
