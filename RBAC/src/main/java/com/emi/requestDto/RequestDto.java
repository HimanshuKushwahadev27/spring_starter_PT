package com.emi.requestDto;

import java.util.Set;

import com.emi.enums.Role;

import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@XmlRootElement
public class RequestDto {

	private String firstName;
	private String lastName;
	private String password;
	private String email;
	private Set<Role> roles;
}
