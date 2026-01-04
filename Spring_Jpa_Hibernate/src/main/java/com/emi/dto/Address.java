package com.emi.dto;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
//signifies that this class doesn't have its own table and will be embedded in another table
@Embeddable
public class Address {
 
	private String city;
	private int pincode;
	private String state;
	private String country;
}
