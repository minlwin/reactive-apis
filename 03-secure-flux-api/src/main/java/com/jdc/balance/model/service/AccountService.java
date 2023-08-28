package com.jdc.balance.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.jdc.balance.model.entity.Account;
import com.jdc.balance.model.form.SignUpForm;
import com.jdc.balance.model.repo.AccountRepo;

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
}
