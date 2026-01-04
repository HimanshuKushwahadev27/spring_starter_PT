package com.emi.service;
import java.util.List;

import com.emi.dto.Employee;
public interface EmployeeService {
   
	public List<Employee> getAllEmployees();
	public List<Employee> addEmployee(Employee emp);
}
