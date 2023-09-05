package com.jdc.balance.api.member;

import java.util.Optional;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jdc.balance.model.consts.BalanceType;
import com.jdc.balance.model.dto.Ledger;

import reactor.core.publisher.Flux;

@RestController
@RequestMapping("ledgers")
public class LedgerApi {

	@GetMapping
	Flux<Ledger> search(@RequestParam Optional<BalanceType> type) {
		return null;
	}
}
