package com.elite.customer.config;

import java.util.Date;

import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;


@Component
public class JwtUtil {
	
	private String jwtSecret = "secret";

	private long tokenValidity = 5 * 60 * 60;

	public String generateToken(String long1) {
		Claims claims = Jwts.claims().setSubject(long1);
		long nowMillis = System.currentTimeMillis();
		long expMillis = nowMillis + tokenValidity * 1000;
		Date exp = new Date(expMillis);
		return Jwts.builder().setClaims(claims).setIssuedAt(new Date(nowMillis)).setExpiration(exp)
				.signWith(SignatureAlgorithm.HS512, jwtSecret).compact();
	}
}