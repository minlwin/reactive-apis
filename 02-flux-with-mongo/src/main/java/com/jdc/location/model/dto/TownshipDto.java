package com.jdc.location.model.dto;

import com.jdc.location.model.entity.Division;

public record TownshipDto(
		String id,
		String name,
		Division division
		) {

}
