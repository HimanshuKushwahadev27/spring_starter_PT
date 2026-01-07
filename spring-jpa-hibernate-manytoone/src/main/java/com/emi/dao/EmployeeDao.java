package com.emi.dao;

import java.util.List;

import com.emi.dto.Department;
import com.emi.dto.Employee;

public interface EmployeeDao {

	public List<Employee> getAllEmpByDeptId( int deptId);
	
	public boolean addEmp(Employee employee , int deptId);
	
	public boolean addDep(Department deptartment);
	
	public  List<Department> getAllDep() ;
	
	
}
