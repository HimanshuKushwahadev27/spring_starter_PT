package com.emi.service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import javax.crypto.SecretKey;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.emi.config.JwtProperties;
import com.emi.entity.User;
import com.emi.securitee.UserPrincipal;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class JwtService {
	
	private final JwtProperties properties;
	
	public String getUserName(String jwt) {
		return extractClaim(jwt , Claims::getSubject);
	}
	
	private SecretKey signInKey() {
	  
		byte[] keyBytes=Decoders.BASE64.decode(properties.getSecret());
		return Keys.hmacShaKeyFor(keyBytes);
	}
	
	
	//all claims from the token parsed
	public Claims getAllClaims(String jwt) {
		return 	Jwts.parser()
				.verifyWith(signInKey())
				.build()
				.parseSignedClaims(jwt)
				.getPayload();
	}
	
	public String generateToken(User user) {
		UserDetails userdetails=new UserPrincipal(user);
		return generateToken(new HashMap<>(),userdetails);
	}
	
	public boolean isTokenValid(String token , UserDetails userdetails) {
		final String username=getUserName(token);
		return (username.equals(userdetails.getUsername())&& !isTokenExpire(token));
	}
	
	private boolean isTokenExpire(String token) {
		return extractExpiration(token).before(new Date());
	}

	private java.util.Date extractExpiration(String token) {
		return extractClaim(token , Claims::getExpiration);
	}

	public String generateToken(Map<String , Object> extraClaims
			,UserDetails userdetais ) {
		
		return  Jwts
				.builder()
				.claims(extraClaims)
				.subject(userdetais.getUsername())
				.issuedAt(new Date(System.currentTimeMillis()))
				.expiration(new Date(System.currentTimeMillis() + properties.getExpiration()))
				.signWith(signInKey())
				.compact();
	}
		
		public  <T> T extractClaim(
				 String jwt ,
				 Function<Claims ,T> claimsResolver)
		{
			Claims claim=getAllClaims(jwt); 
			return claimsResolver.apply(claim);
		}
	
}
