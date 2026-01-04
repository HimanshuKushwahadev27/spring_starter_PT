package com.emi.service;
import java.util.List;

import com.emi.dto.Employee;
public interface EmployeeService {
   
	public boolean updateEmployee(Employee emp);
	public List<Employee> getAllEmployees();
	public boolean addEmployee(Employee emp);
	public boolean deleteEmployee(int empId);
}
