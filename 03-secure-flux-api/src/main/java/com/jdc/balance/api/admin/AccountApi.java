package com.jdc.balance.api.admin;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jdc.balance.model.consts.Role;
import com.jdc.balance.model.dto.AccountDto;
import com.jdc.balance.model.service.AccountService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("admin/accounts")
public class AccountApi {

	@Autowired
	private AccountService service;
	
	@GetMapping
	Flux<AccountDto> search(
			@RequestParam Optional<Role> role, 
			@RequestParam Optional<String> keyword) {
		return service.search(role, keyword);
	}
	
	@GetMapping("{id}")
	Mono<AccountDto> findById(@PathVariable String id) {
		return service.findById(id);
	}

}
