package com.emi.service;



public interface EmployeeService {
  
	public java.util.List<com.emi.dto.Employee> getAllEmployees();
	public boolean addEmployee(com.emi.dto.Employee emp);
	public boolean updateEmployee(Integer id ,com.emi.dto.Employee emp);
	public boolean deleteEmployee(int empId);
	public boolean getEmployeeById(int empId);
}
