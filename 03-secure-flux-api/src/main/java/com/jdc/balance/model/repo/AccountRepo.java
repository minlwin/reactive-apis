package com.jdc.balance.model.repo;

import com.jdc.balance.model.entity.Account;
import com.jdc.balance.model.repo.base.BaseRepository;

import reactor.core.publisher.Mono;

public interface AccountRepo extends BaseRepository<Account, String>{

	Mono<Account> findOneBySignInUsername(String username);

}
