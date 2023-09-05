package com.jdc.balance.model.entity.audit;

import java.time.LocalDateTime;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import lombok.Data;

@Data
public class AuditInfo  {
	
	private boolean deleted;
	
	@CreatedBy
	private String createdBy;
	
	@CreatedDate
	private LocalDateTime creation;
	
	@LastModifiedBy
	private String modifiedBy;
	
	@LastModifiedDate
	private LocalDateTime modification;
}
