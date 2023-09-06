package com.jdc.balance.model.service;

import java.security.Principal;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.security.core.context.ReactiveSecurityContextHolder;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.stereotype.Service;

import com.jdc.balance.model.consts.BalanceType;
import com.jdc.balance.model.entity.Ledger;
import com.jdc.balance.model.form.LedgerForm;
import com.jdc.balance.model.repo.AccountRepo;
import com.jdc.balance.model.repo.LedgerRepo;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class LedgerService {

	@Autowired
	private LedgerRepo ledgerRepo;
	@Autowired
	private AccountRepo accountRepo;
	
	public Flux<Ledger> search(Optional<BalanceType> type, Optional<String> name) {
		
		return ReactiveSecurityContextHolder.getContext().map(SecurityContext::getAuthentication)
			.map(a -> a.getName())
			.flatMap(username -> accountRepo.findOneBySignInUsername(username))
			.map(account -> Criteria.where("account.id").is(account.id())
					.andOperator(
					type.map(value -> Criteria.where("type").is(value)).orElse(Criteria.where(null)),
					name.map(value -> Criteria.where("name").regex("%s.*".formatted(value), "i")).orElse(Criteria.where(null))
				)
			).flatMapMany(criteria -> ledgerRepo.select(new Query(criteria)));
			
	}

	public Mono<Ledger> create(LedgerForm form) {
		return ReactiveSecurityContextHolder.getContext().map(SecurityContext::getAuthentication)
			.map(Principal::getName)
			.flatMap(username -> accountRepo.findOneBySignInUsername(username))
			.map(account -> form.document(account))
			.flatMap(doc -> ledgerRepo.save(doc));
	}

	public Mono<Ledger> findById(String id) {
		return ledgerRepo.findById(id);
	}

	public Mono<Ledger> update(String id, LedgerForm form) {
		return ledgerRepo.findById(id).flatMap(doc -> ledgerRepo.save(doc.name(form.name()).type(form.type())));
	}

}
