package com.emi.dto;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@XmlRootElement
@Entity
public class Employee {

	private String empName;
	@Id
	private int empId;
	
	private double empSalary;
	
	private int workinghrs;
	
}
