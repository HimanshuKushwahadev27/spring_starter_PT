package com.emi.config;
import java.io.IOException;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.emi.repo.TokenRepo;
import com.emi.services.JwtServices;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class JwtAuthFilter extends OncePerRequestFilter{  

	private final JwtServices jwtService;
	private final UserDetailsService userDetailsService;
   	private final TokenRepo tokenRepo;
	
	protected void doFilterInternal(
			HttpServletRequest request,
			HttpServletResponse response,
			FilterChain filterChain) throws ServletException , IOException{
		
		final String authHeader=request.getHeader("Authorization");
		final String jwt;
		final String userEmail;
		
		
		if(authHeader==null || !authHeader.startsWith("Bearer ")) {
			filterChain.doFilter(request, response);
			return;
		}
		
		jwt=authHeader.substring(7);
		userEmail=jwtService.getUserName(jwt);
		
		if(userEmail!=null && SecurityContextHolder.getContext().getAuthentication()==null) {
			//getting information of the user from database if available 
			UserDetails userDetails=this.userDetailsService.loadUserByUsername(userEmail);
			
			var tokenValid=tokenRepo.findByToken(jwt)
					.map(t -> !t.isExpired() && !t.isRevoked())
					.orElse(false);
			
			if(jwtService.isTokenValid(jwt, userDetails) && tokenValid) {
				
				//creating authenticated user identity
				UsernamePasswordAuthenticationToken auth=new UsernamePasswordAuthenticationToken(
						userDetails,
						null,
						userDetails.getAuthorities());
				//adds IPaddress and sessioniD
				auth.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				
				 SecurityContextHolder.getContext().setAuthentication(auth);

			}
		}
		filterChain.doFilter(request, response);
	}
}
