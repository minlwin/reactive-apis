package com.jdc.balance.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.security.authentication.UserDetailsRepositoryReactiveAuthenticationManager;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.server.SecurityWebFilterChain;

import com.jdc.balance.security.ApplicationUserDetailsService;

@Configuration
public class SecurityConfig {
	
	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	ReactiveAuthenticationManager authenticationManager(ApplicationUserDetailsService userDetailsService) {
		var manager = new UserDetailsRepositoryReactiveAuthenticationManager(userDetailsService);
		manager.setPasswordEncoder(passwordEncoder());
		return manager;
	}

	@Bean
	SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity http) {

		http.csrf(csrf -> csrf.disable());
		
		http.authorizeExchange(exc -> {
			exc.pathMatchers("/security/**").permitAll();
			exc.pathMatchers("/admin/**").hasAuthority("Admin");
			exc.pathMatchers("/member/**").hasAuthority("Member");
			exc.anyExchange().denyAll();
		});
		
		return http.build();
	}
}
