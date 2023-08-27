package com.jdc.balance.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.jdc.balance.model.consts.Role;
import com.jdc.balance.model.form.SignUpForm;
import com.jdc.balance.model.repo.AccountRepo;

import reactor.core.publisher.Mono;

@Configuration
public class AdminUserInitializer {

	@Autowired
	private AccountRepo repo;
	@Autowired
	private PasswordEncoder encoder;
	
	private Logger logger = LoggerFactory.getLogger(AdminUserInitializer.class);
	
	@EventListener(classes = ContextRefreshedEvent.class)
	public void initAdminUser() {
		logger.info("Admin Initializer is worked.");
		repo.count().flatMap(count -> {
			if(count == 0) {
				var signUp = new SignUpForm("Admin User", "admin@gmail.com", "admin");
				var admin = signUp.convert(encoder::encode).withRole(Role.Admin);
				return repo.save(admin);
			}
			return Mono.empty();
		}).subscribe(account -> logger.info("Admin has been created with id : %s".formatted(account.id())));
		
	}
}
