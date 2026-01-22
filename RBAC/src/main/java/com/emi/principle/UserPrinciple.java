package com.emi.principle;

import java.util.Collection;

import org.jspecify.annotations.Nullable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.emi.entity.User;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class UserPrinciple implements UserDetails {
	
	private final User user;

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
	    return user.getRole()
	            .stream()
	            .map(role -> new SimpleGrantedAuthority("ROLE_" + role.name()))
	            .toList();
	}
	@Override
	public  String getPassword() {
		return user.getPassword();
	}

	@Override
	public String getUsername() {
		return user.getEmail();
	}

}
