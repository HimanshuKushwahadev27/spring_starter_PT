package com.emi.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.emi.dto.Employee;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

@JacksonXmlRootElement
@Component
public class EmployeeServiceImpl implements EmployeeService {


    public List<Employee> employees = new ArrayList<>();
    
    
    {
		employees.add(new Employee(1, "John Doe", "123 Main St", 50000));
		employees.add(new Employee(2, "Jane Smith", "456 Oak Ave", 60000));
		employees.add(new Employee(3, "Mike Johnson", "789 Pine Rd", 55000));
	}
    
	@Override
	public List<Employee> getAllEmployees() {
		return employees;
	}
	@Override
	public List<Employee> addEmployee(Employee emp){
		employees.add(emp);
		return employees;
	}

}
