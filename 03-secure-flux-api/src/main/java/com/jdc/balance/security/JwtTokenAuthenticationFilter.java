package com.jdc.balance.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.context.ReactiveSecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;

import reactor.core.publisher.Mono;

@Service
public class JwtTokenAuthenticationFilter implements WebFilter{
	
	@Autowired
	private JwtTokenProvider provider;
	
	@Value("${token.header.name}")
	private String headerName;

	@Override
	public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain) {
		
		var token = exchange.getRequest().getHeaders().getFirst(headerName);
		var authentication = provider.authenticate(token);
		
		if(null != authentication) {
			return chain.filter(exchange).contextWrite(ReactiveSecurityContextHolder.withAuthentication(authentication));
		}
		
		return chain.filter(exchange);
	}

}
