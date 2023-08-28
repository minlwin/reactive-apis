package com.jdc.balance.security;

import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.stream.Collectors;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtTokenProvider {

	@Value("${token.issuer}")
	private String issuer;
	@Value("${token.maxlife}")
	private int maxLife;
	@Value("${token.prefix}")
	private String tokenPrefix;
	
	private SecretKey key = Keys.secretKeyFor(SignatureAlgorithm.HS512);

	public String generate(Authentication authentication) {

		var expireAt = Calendar.getInstance();
		expireAt.add(Calendar.MINUTE, maxLife);

		if (null != authentication) {
			var token = Jwts.builder().signWith(key).setSubject(authentication.getName()).setIssuer(issuer)
					.setIssuedAt(new Date()).setExpiration(expireAt.getTime()).claim("rol", authentication
							.getAuthorities().stream().map(a -> a.getAuthority()).collect(Collectors.joining(",")))
					.compact();
			
			return "%s %s".formatted(tokenPrefix, token);
		}

		return null;
	}
	
	public Authentication authenticate(String token) {
		
		if(StringUtils.hasLength(token) && token.startsWith(tokenPrefix)) {
			var claims = Jwts.parserBuilder().requireIssuer(token).setSigningKey(key).build()
				.parseClaimsJws(token);
			
			var username = claims.getBody().getSubject();
			var rolString = claims.getBody().get("rol").toString();
			var authorities =Arrays.stream(rolString.split(",")).map(SimpleGrantedAuthority::new).toList();
			
			return UsernamePasswordAuthenticationToken.authenticated(username, null, authorities);
		}
		
		return null;
	}
}
