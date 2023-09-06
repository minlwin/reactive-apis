package com.jdc.balance.api.member;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jdc.balance.model.consts.BalanceType;
import com.jdc.balance.model.entity.Ledger;
import com.jdc.balance.model.form.LedgerForm;
import com.jdc.balance.model.service.LedgerService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("member/ledgers")
public class LedgerApi {
	
	@Autowired
	private LedgerService service;

	@GetMapping
	Flux<Ledger> search(
			@RequestParam Optional<BalanceType> type,
			@RequestParam Optional<String> name) {
		return service.search(type, name);
	}
	
	@PostMapping
	Mono<Ledger> create(@Validated @RequestBody LedgerForm form) {
		return service.create(form);
	}
	
	@GetMapping("{id}")
	Mono<Ledger> findById(@PathVariable String id) {
		return service.findById(id);
	}
	
	@PutMapping("{id}")
	Mono<Ledger> update(@PathVariable String id, @Validated @RequestBody LedgerForm form) {
		return service.update(id, form);
	}
}
