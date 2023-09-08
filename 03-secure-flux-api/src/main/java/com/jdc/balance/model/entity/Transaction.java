package com.jdc.balance.model.entity;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

import com.jdc.balance.model.entity.audit.AuditInfo;

@Document
public record Transaction(
		@Id String id, 
		@DocumentReference Ledger ledger, 
		LocalDate issueAt,
		@DocumentReference Account account, 
		List<TransactionItem> items, 
		AuditInfo audit) {

	public Transaction(Ledger ledger, LocalDate issueAt, Account account) {
		this(null, ledger, issueAt, account, null, new AuditInfo());
	}

	public Transaction ladger(Ledger ledger) {
		return new Transaction(id, ledger, issueAt, account, items, audit);
	}
	
	public Transaction issueAt(LocalDate issueAt) {
		return new Transaction(id, ledger, issueAt, account, items, audit);
	}

	public Transaction items(List<TransactionItem> items) {
		return new Transaction(id, ledger, issueAt, account, items, audit);
	}
}
