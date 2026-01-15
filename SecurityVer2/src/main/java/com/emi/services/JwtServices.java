package com.emi.services;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import javax.crypto.SecretKey;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.emi.Security.UserPrinciple;
import com.emi.config.JwtProperties;
import com.emi.entity.User;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class JwtServices {

	private final JwtProperties properties;
	
	public String getUserName(String jwt) {
		return extractClaim(jwt , Claims::getSubject);
	}
	
	private SecretKey getSignInKey() {
		//takes the Base64 encoded key and convert it ito bytes as cryptographic algos works on bytes
		byte[] keyBytes=Decoders.BASE64.decode(properties.getSecret());
		//converted into secret key
		return Keys.hmacShaKeyFor(keyBytes);
	}
	
	public Claims getAllClaims(String jwt) {
		//split header
		return Jwts.parser() // creates jwt parser builder(Signature verification ,Token integrity checks , decodes header and payload)
				.verifyWith(getSignInKey()) // sets secrest key that is used to verify the jwt signature
				.build()//makes the parser immutable used to validate tokens
				.parseSignedClaims(jwt) //checks jwt format , verifies signaturedecode Base64 parts
				.getPayload();//return the claims
	}
	
	//generic method that can extract any claim
	public <T> T extractClaim(
			String jwt ,
			Function<Claims , T> claimsResolver ) {
		Claims claim=getAllClaims(jwt);
		return claimsResolver.apply(claim);
	}
	
	public String generateToken(User user) {
		UserDetails userDetails=new UserPrinciple(user);
		return generateToken(new HashMap<>() , userDetails);
	}
	
	public String generateToken(Map<String , Object> extraClaims
			,UserDetails userDetail) {
		return Jwts.builder()
				.claims(extraClaims)
				.subject(userDetail.getUsername())
				.issuedAt(new Date(System.currentTimeMillis()))
				.expiration(new Date(System.currentTimeMillis() + properties.getExpiration()))
				.signWith(getSignInKey())
				.compact();
	}
	
	public boolean isTokenValid(String token , UserDetails userDetails) {
		final String userName=getUserName(token);
		return (userName.equals(userDetails.getUsername()) && !isTokenExpire(token));
	}
	
	public boolean isTokenExpire(String Token) {
		return getExpirationDate(Token).before(new Date());
	}
	
	public Date getExpirationDate(String token) {
		return extractClaim(token , Claims::getExpiration);
	}
}
