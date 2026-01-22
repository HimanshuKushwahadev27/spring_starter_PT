package com.emi.service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

import javax.crypto.SecretKey;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.emi.config.JwtProperties;
import com.emi.entity.User;
import com.emi.principle.UserPrinciple;

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
		return extractClaims(jwt , Claims::getSubject);
	}
	
	public SecretKey getSignInKey() {
		byte[] keyByte=Decoders.BASE64.decode(properties.getToken());
		return Keys.hmacShaKeyFor(keyByte);
	}
	
	public Claims getAllClaims(String jwt) {
		return Jwts.parser()
				.verifyWith(getSignInKey())
				.build()
				.parseSignedClaims(jwt)
				.getPayload();
	}
	
	public <T> T extractClaims(
			String jwt,
			Function<Claims, T> claimsResolver) {
		Claims claim=getAllClaims(jwt);
		return claimsResolver.apply(claim);
	}
	
	
	public String generateToken(User user) {
		
		HashMap<String , Object> maps=new HashMap<>();
		
		UserDetails userDetails=new UserPrinciple(user);
		
		List<String> roles=userDetails.getAuthorities()
				.stream()
				.map(GrantedAuthority::getAuthority)
				.toList();
		
		maps.put("roles" , roles);
		
		return generateToken(maps, userDetails);
	}
	
	public String generateToken(Map<String , Object> extraClaims,
			                        UserDetails userDetails) {
		return Jwts.builder()
				.claims(extraClaims)
				.subject(userDetails.getUsername())
				.issuedAt(new Date(System.currentTimeMillis()))
				.expiration(new Date(System.currentTimeMillis()+properties.getExpiration()))
				.signWith(getSignInKey())
				.compact();		
	}
	
	public boolean isTokenValid(String jwt) {
		return  !isTokenExpired(jwt);
	}
	
	public boolean isTokenExpired(String jwt) {
		return getExpirationDate(jwt).before(new Date(System.currentTimeMillis()));
	}
	public Date getExpirationDate(String jwt) {
		return extractClaims(jwt , Claims::getExpiration);
	}
	
	public List<String> extractRole(String jwt) {
		Claims claim=getAllClaims(jwt);
		
		Object roleObj=claim.get("roles");
		
		if(roleObj instanceof  List<?>) {
			return ((List<?>) roleObj)
					.stream()
					.map(Object::toString)
					.toList()
					;
		}
		
		return List.of();
	}
			
}
