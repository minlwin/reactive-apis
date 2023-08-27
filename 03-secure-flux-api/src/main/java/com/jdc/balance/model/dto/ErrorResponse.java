package com.jdc.balance.model.dto;

import java.util.List;

import com.jdc.balance.model.consts.ErrorType;

public record ErrorResponse(
		ErrorType type,
		List<String> messages
		) {

}
