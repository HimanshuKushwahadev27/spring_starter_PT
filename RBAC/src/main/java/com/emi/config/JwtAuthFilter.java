package com.emi.config;

import java.io.IOException;
import java.util.Collection;
import java.util.List;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.GrantedAuthority;

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
public class JwtAuthFilter extends OncePerRequestFilter{

	private final JwtService jwtService;

	
	@Override
	protected void doFilterInternal(
			HttpServletRequest request,
			HttpServletResponse response,
			FilterChain filterChain)
			throws ServletException, IOException {
		
		final String auth=request.getHeader("Authorization");
		final String jwt;
		
		if(auth==null || !auth.startsWith("Bearer ")) {
			filterChain.doFilter(request, response);
			return;
		}
		
		jwt=auth.substring(7);
		String userName=jwtService.getUserName(jwt);
		List<String> roles=jwtService.extractRole(jwt);
		
		Collection<? extends GrantedAuthority> authorities= roles.stream().map(SimpleGrantedAuthority::new).toList();
		
		if(userName!=null && SecurityContextHolder.getContext().getAuthentication()==null) {
			
			if(jwtService.isTokenValid(jwt)) {
				UsernamePasswordAuthenticationToken autho=new UsernamePasswordAuthenticationToken(
						userName,
						null,
						authorities);
				SecurityContextHolder.getContext().setAuthentication(autho);
			}
		}
		filterChain.doFilter(request, response);
		
	}

}
