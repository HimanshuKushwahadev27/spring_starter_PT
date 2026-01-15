package com.emi.entity;

import java.time.LocalDateTime;

import com.emi.enums.Role;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name="users")
public class User {
	
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE)
	private Long user_Id;
	
	private String firstName;
	
	private String lastName;
	
	private String password;
	
	private Role role;
	
	private String email;
	
	private boolean isEnabled;
	
	private boolean accountNonLocked;
	
	private LocalDateTime passwordExpireDate;
	

}
