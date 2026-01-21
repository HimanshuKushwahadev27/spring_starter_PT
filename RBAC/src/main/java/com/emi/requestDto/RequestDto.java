package com.emi.requestDto;

import java.util.Set;

import com.emi.enums.Role;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class RequestDto {

	private String firstName;
	private String lastName;
	private String password;
	private String email;
	private Set<Role> roles;
}
