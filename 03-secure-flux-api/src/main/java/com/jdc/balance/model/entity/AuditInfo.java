package com.jdc.balance.model.entity;

import java.time.LocalDateTime;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

public record AuditInfo(
		boolean deleted,
		@CreatedBy
		String createdBy,
		@CreatedDate
		LocalDateTime creation,
		@LastModifiedBy
		String modifiedBy,
		@LastModifiedDate
		LocalDateTime modification
		) {

}
