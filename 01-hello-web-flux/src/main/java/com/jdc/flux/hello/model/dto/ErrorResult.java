package com.jdc.flux.hello.model.dto;

import java.util.List;

public record ErrorResult(
		Type type,
		List<String> messages
		) {

	public enum Type {
		Validation, Business, Platform
	}
}
