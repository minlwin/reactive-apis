package com.jdc.balance.api.admin;

import java.util.Optional;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jdc.balance.model.consts.Role;
import com.jdc.balance.model.dto.AccountDto;

import reactor.core.publisher.Flux;

@RestController
@RequestMapping("admin/accounts")
public class AccountApi {
	
	@GetMapping
	Flux<AccountDto> search(
			@RequestParam Optional<Role> role, 
			@RequestParam Optional<String> keyword) {
		return null;
	}

}
