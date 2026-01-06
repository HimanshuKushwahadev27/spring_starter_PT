package com.emi.dao;

import java.util.List;

import com.emi.dto.Department;
import com.emi.dto.Employee;

public interface EmployeeDao {

	public List<Employee> getAllEmployeeByDepartmentId(int deptId);
	public boolean addEmployee(Employee emp, int deptId);
	public boolean addDepartment(Department dept);
	public boolean getEmployeeById(int empId);
}
