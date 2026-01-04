package com.emi.dao;

public interface EmployeeDao {
	
	public boolean addEmp(com.emi.dto.Employee emp);
	public boolean updateEmp(Integer id ,com.emi.dto.Employee emp);
	public boolean deleteEmp(int empId);
	public java.util.List<com.emi.dto.Employee> getAllEmp();
	public boolean getEmpById(int empId);

}
