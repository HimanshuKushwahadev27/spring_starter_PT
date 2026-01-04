package com.emi.dto;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import jakarta.validation.constraints.NotBlank;

@JacksonXmlRootElement
public class Employee {
   private int EmpId;
   //ensuring it isnt blank
   @NotBlank(message="Employee name cannot be blank")
   private String EmpName;
   
   private String address;
   private float salary;

   public Employee() {}
   
   public Employee(int empId, String empName, String address, float salary) {
	super();
	EmpId = empId;
	EmpName = empName;
	this.address = address;
	this.salary = salary;
}

public int getEmpId() {
	return EmpId;
}

public void setEmpId(int empId) {
	EmpId = empId;
}

public String getEmpName() {
	return EmpName;
}

public void setEmpName(String empName) {
	EmpName = empName;
}

public String getAddress() {
	return address;
}

public void setAddress(String address) {
	this.address = address;
}

public float getSalary() {
	return salary;
}

public void setSalary(float salary) {
	this.salary = salary;
}
   
   
   
   
   
}
