package com.jdc.balance.model.service;

import static org.springframework.data.mongodb.core.query.Criteria.where;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.jdc.balance.model.consts.Role;
import com.jdc.balance.model.dto.AccountDto;
import com.jdc.balance.model.entity.Account;
import com.jdc.balance.model.form.SignUpForm;
import com.jdc.balance.model.repo.AccountRepo;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class AccountService {

	@Autowired
	private AccountRepo repo;
	@Autowired
	private PasswordEncoder encoder;
	
	public Mono<Account> signUpAccount(SignUpForm form) {
		return repo.save(form.convert(encoder::encode));
	}

	public Flux<AccountDto> search(Optional<Role> role, Optional<String> keyword) {
		var criteria = role.map(
				value -> where("role").is(value)).orElse(where(null)).andOperator(
						keyword.map(value -> where("name").regex("%s.*".formatted(value), "i"))
						.orElse(where(null)));
		return repo.select(Query.query(criteria)).map(AccountDto::from);
	}

	public Mono<AccountDto> findById(String id) {
		return repo.findById(id).map(null);
	}
}
