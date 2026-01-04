package com.emi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.emi.dao.EmployeeDao;
import com.emi.dto.Employee;


@Component("employeeServiceImpl")
public class EmployeeServiceImpl implements EmployeeService {

	EmployeeDao employeeDao;
	
	EmployeeServiceImpl(){}
	
	@Autowired
	EmployeeServiceImpl(EmployeeDao employeeDao){
				this.employeeDao=employeeDao;
	}
	
	@Override
	public List<Employee> getAllEmployees() {
		return employeeDao.getAllEmp();
	}

	@Override
	public boolean addEmployee(Employee emp) {
		return employeeDao.addEmp(emp);
	}

	@Override
	public boolean updateEmployee(Integer id,Employee emp) {
		return employeeDao.updateEmp(id,emp);
	}

	@Override
	public boolean deleteEmployee(int empId) {
		return employeeDao.deleteEmp(empId);
	}

	@Override
	public boolean getEmployeeById(int empId) {
		return employeeDao.getEmpById(empId);
	}

}
