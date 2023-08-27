package com.jdc.balance.security;

import java.util.Calendar;
import java.util.Date;
import java.util.stream.Collectors;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtTokenProvider {

	@Value("${token.issuer}")
	private String issuer;
	@Value("${token.maxlife}")
	private int maxLife;
	private SecretKey key = Keys.secretKeyFor(SignatureAlgorithm.HS512);

	public String generate(Authentication authentication) {

		var expireAt = Calendar.getInstance();
		expireAt.add(Calendar.MINUTE, maxLife);

		if (null != authentication) {
			return Jwts.builder().signWith(key).setSubject(authentication.getName()).setIssuer(issuer)
					.setIssuedAt(new Date()).setExpiration(expireAt.getTime()).claim("rol", authentication
							.getAuthorities().stream().map(a -> a.getAuthority()).collect(Collectors.joining(",")))
					.compact();
		}

		return null;
	}
}
