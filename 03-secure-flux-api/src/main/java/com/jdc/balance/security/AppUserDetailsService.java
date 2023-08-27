package com.jdc.balance.security;

import org.springframework.security.core.userdetails.ReactiveUserDetailsService;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.jdc.balance.model.repo.AccountRepo;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class AppUserDetailsService implements ReactiveUserDetailsService {

	private final AccountRepo repo;
	
	@Override
	public Mono<UserDetails> findByUsername(String username) {
		return repo.findOneBySignInUserName(username)
				.map(a -> User.withUsername(username)
						.password(a.signIn().password())
						.authorities(a.role().name())
						.build());
	}

}
