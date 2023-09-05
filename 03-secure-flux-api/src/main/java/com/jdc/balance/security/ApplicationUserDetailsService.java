package com.jdc.balance.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.ReactiveUserDetailsService;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.jdc.balance.model.repo.AccountRepo;

import reactor.core.publisher.Mono;

@Service
public class ApplicationUserDetailsService implements ReactiveUserDetailsService{

	@Autowired
	private AccountRepo accountRepo;
	
	@Override
	public Mono<UserDetails> findByUsername(String username) {
		return accountRepo.findOneBySignInUsername(username)
				.map(a -> User.withUsername(username)
						.authorities(a.role().name())
						.password(a.signIn().password())
						.build());	
	}
}
