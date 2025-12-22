package com.chiu.projects.configuration;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Component
public class TokenManager {
	private final static long TOKEN_VALIDITY = 10 * 60 * 60;
	
	@Value("${secret}")
	private String jwtSecret;
	
	public String generateJwtToken(UserDetails userDetails) {
		Map<String, Object> claims = new HashMap<String, Object>();
		
		return Jwts
				.builder()
				.setClaims(claims)
				.setSubject(userDetails.getUsername())
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + TOKEN_VALIDITY * 1000))
				.signWith(getKey(), SignatureAlgorithm.HS256)
				.compact();
	}
	
	public Boolean validateJwtToken(String token, UserDetails userDetails) {
		String userName = getUserNameFromToken(token);
		
		Claims claims = getClaims(token);
		
		Boolean isTokenExpired = claims.getExpiration().before(new Date());
		return userName.equals(userDetails.getUsername()) && !isTokenExpired;
	}
	
	private Claims getClaims(String token) {
		Claims claims = Jwts
				.parserBuilder()
				.setSigningKey(getKey())
				.build()
				.parseClaimsJws(token)
				.getBody();
		
		return claims;
	}
	
	public String getUserNameFromToken(String token) {
		Claims claims = getClaims(token);
		
		return claims.getSubject();
	}
	
	private Key getKey() {
		byte [] keyBytes = Decoders.BASE64.decode(jwtSecret);
		Key key = Keys.hmacShaKeyFor(keyBytes);
		return key;
	}
}
