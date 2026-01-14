package com.emi.securitee;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

import org.jspecify.annotations.Nullable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.emi.entity.User;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor

public class UserPrincipal implements UserDetails {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final User user;
	
	

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
	
		return List.of(new SimpleGrantedAuthority("ROLE_" + user.getRole()));
	}


	@Override
	public @Nullable String getPassword() {
		return user.getPassword();
	}


	@Override
	public String getUsername() {
		return user.getFirstName();
	}
	
	public boolean isAccountNonLocked() {
		return user.isAccountNonLocked();
	}
	
	public boolean isCredentialsNonExpired() {
		return user.getPasswrodExpiryDate() == null ||
				user.getPasswrodExpiryDate().isAfter(LocalDateTime.now());
	}
	
	public  boolean isEnabled() {
		return user.isEnabled();
	}


}
