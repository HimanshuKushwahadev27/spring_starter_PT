package com.emi.requestDto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor

public class RegisterRequest {

	private String firstName;
	private String lastName;
	private String email;
	private String password;
}
