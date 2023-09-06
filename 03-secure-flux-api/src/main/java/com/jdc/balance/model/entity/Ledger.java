package com.jdc.balance.model.entity;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

import com.jdc.balance.model.consts.BalanceType;
import com.jdc.balance.model.entity.audit.AuditInfo;
import com.jdc.balance.model.entity.audit.AuditableDocument;

@Document
public record Ledger(
		String id,
		@DocumentReference
		Account account,
		BalanceType type,
		String name,
		AuditInfo audit
		) implements AuditableDocument{
	
	public Ledger(Account account, BalanceType type, String name) {
		this(null, account, type, name, new AuditInfo());
	}
	
	public Ledger type(BalanceType type) {
		return new Ledger(id,  account, type, name, audit);
	}
	
	public Ledger name(String name) {
		return new Ledger(id,  account, type, name, audit);
	}
}
