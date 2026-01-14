package com.emi.config;

import java.io.IOException;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.emi.service.JwtService;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;


@Component
@RequiredArgsConstructor
//can also user Filter
public class JwtAuthenticationFilter extends OncePerRequestFilter{

	private final JwtService jwtService;
	private final UserDetailsService userDetailService;
	

	@Override
	protected void doFilterInternal(
			HttpServletRequest request,
		    HttpServletResponse response,
			FilterChain filterChain)
			throws ServletException, IOException {
		
		
		final String authHeader=request.getHeader("Authorization");
		final String jwt;
		final String userEmail;
		String path=request.getRequestURI();
		
		if(path.startsWith("/api/v1/auth/")) {
			filterChain.doFilter(request, response);
			return;
		}
		
		

		
		if(authHeader==null || !authHeader.startsWith("Bearer ")) {
			filterChain.doFilter(request, response);
			return;
		}
		//token starts from 7th index 
		jwt = authHeader.substring(7);
		userEmail=jwtService.getUserName(jwt);
		
		//checking if the user is already authenticated in the condition below.
		//so we have user email and user is not authenticated
		if(userEmail!=null && SecurityContextHolder.getContext().getAuthentication()==null) {
			//getting information of the user from database if available 
			UserDetails userdetails=this.userDetailService.loadUserByUsername(userEmail);
			
			//if user valid 
			//comparing the users stored information with the information in request
			if(jwtService.isTokenValid(jwt, userdetails)) {
				//for updating security context
				UsernamePasswordAuthenticationToken authToken=new UsernamePasswordAuthenticationToken(
						userdetails,
						null,
						userdetails.getAuthorities()
				);
				//enforcing the token with our request 

				authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				//updating security context
				System.out.println(">>> SETTING AUTH FOR USER: " + userEmail);

				 SecurityContextHolder.getContext().setAuthentication(authToken);
				 
				 System.out.println(">>> AUTH SET = " +
						    SecurityContextHolder.getContext().getAuthentication());

			}
		}
		filterChain.doFilter(request, response);
	}

}
