package com.jdc.flux.hello.model.dto;

import com.jdc.flux.hello.model.entity.Division;

public record TownshipDto(
		Integer id,
		String name,
		Division division
		) {

}
