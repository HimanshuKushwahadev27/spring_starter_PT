package com.emi.dto;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@XmlRootElement
@AllArgsConstructor
@NoArgsConstructor
public class Employee {

	@Id
	private int empId;
	
	private String empName;
	
	private double empSal;
	
	@ManyToOne
	private Department department;
}
