package com.jdc.balance.model.form;

import com.jdc.balance.model.consts.BalanceType;
import com.jdc.balance.model.entity.Account;
import com.jdc.balance.model.entity.Ledger;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record LedgerForm(
		@NotNull(message = "Please select balance type of ledger.")
		BalanceType type,
		@NotEmpty(message = "Please enter ledger name.")
		String name
		) {

	public Ledger document(Account account) {
		return new Ledger(account, type, name);
	}
}
