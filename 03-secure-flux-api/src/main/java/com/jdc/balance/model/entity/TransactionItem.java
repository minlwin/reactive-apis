package com.jdc.balance.model.entity;

public record TransactionItem(
		String item,
		int quantity,
		int price
		) {

}
