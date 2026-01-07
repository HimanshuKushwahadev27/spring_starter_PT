package com.emi.service;

import java.util.List;

import com.emi.dto.Department;
import com.emi.dto.Employee;


public interface EmployeeService {

	public List<Employee> getAllEmployeesByDeptId( int deptId);	

	public boolean addEmployee(Employee employee , int deptId);
	
	public boolean addDepartment(Department deptartment);
	
	public List<Department> getAllDepartments();
}
