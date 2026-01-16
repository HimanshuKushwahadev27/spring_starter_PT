
package com.emi.services;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.stereotype.Service;

import com.emi.repo.TokenRepo;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class LogoutService implements LogoutHandler {

	private final TokenRepo tokenRepo;
	
	@Override
	public void logout(HttpServletRequest request,
			HttpServletResponse response,
			 Authentication authentication) {

		final String authHeader=request.getHeader("Authorization");
		final String jwt;
		
		if(authHeader==null || !authHeader.startsWith("Bearer ")) {
			return;
		}
		jwt=authHeader.substring(7);
		
		var token=tokenRepo.findByToken(jwt)
				.orElse(null);
		
		if(token!=null) {
			token.setExpired(true);
			token.setRevoked(true);
			tokenRepo.save(token);
		}
	}

}
