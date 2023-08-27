package com.jdc.balance.model.repo;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import com.jdc.balance.model.entity.Account;

import reactor.core.publisher.Mono;

public interface AccountRepo extends ReactiveMongoRepository<Account, String>{

	Mono<Account> findOneBySignInUsername(String username);

}
