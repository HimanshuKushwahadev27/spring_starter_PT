package com.emi.service;

import java.util.List;

import com.emi.dto.Employee;

public interface EmployeeService {

	public List<Employee> getAllEmpByDeptId(int deptId);
	public boolean addEmp(Employee emp, int deptId);
	public boolean addDept(com.emi.dto.Department dept);
	public boolean getEmpById(int empId);
	
}
