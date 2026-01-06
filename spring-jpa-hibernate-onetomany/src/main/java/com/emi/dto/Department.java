package com.emi.dto;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@XmlRootElement
@Entity
public class Department {

	@Id
	private int deptId;
	//A department can have multiple employees
	//it tells the hibernate to make changes on the employee and and all the related tables B/W department and employee
	   // when the changes in the department are being made
	
	//though it usually not recommended to this type of relation as the hibernate will not know which table have foreign key
	   //and to fix it it will create a new table comprising of only primary key of both the tables
	   //which leads to redundancy
	
	//cascade = CascadeType.PERSIST : when we save department object the related employee objects will also be saved
	
	@OneToMany(cascade=CascadeType.PERSIST , fetch=FetchType.EAGER)
	private List<Employee> employees;
	
	
	private String deptName;
	
}
