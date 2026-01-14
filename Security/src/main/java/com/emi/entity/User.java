package com.emi.entity;


import java.time.LocalDateTime;

import com.emi.enums.Role;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Entity
@Data
@NoArgsConstructor
@Table(name="users")
public class User  {

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE)
	private Long id;
	
	private String firstName;
	
	private String lastName;
	
	@Enumerated(EnumType.STRING)
	private Role role;
	
	private String password;
	
	private String email;
	
	private boolean isEnabled;
	
	private boolean accountNonLocked;
	
	private LocalDateTime passwrodExpiryDate;
}
