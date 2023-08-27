package com.jdc.balance.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.server.SecurityWebFilterChain;

@Configuration
public class SecurityConfig {
	
	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
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
