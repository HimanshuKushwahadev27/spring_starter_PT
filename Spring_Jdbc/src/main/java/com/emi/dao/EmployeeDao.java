package com.emi.dao;

import java.util.List;

import com.emi.dto.Employee;

public interface EmployeeDao {
	public List<Employee> getAllEmp();
	public boolean updateEmp(Employee emp);
	public boolean addEmp(Employee emp);
    public boolean deleteEmp(int empId);
}
