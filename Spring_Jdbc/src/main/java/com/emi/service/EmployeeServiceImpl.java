package com.emi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.emi.dao.EmployeeDao;
import com.emi.dto.Employee;
@Component
public class EmployeeServiceImpl implements EmployeeService {

    
	EmployeeDao employeeDao;
	
    EmployeeServiceImpl(){}
    
    @Autowired
    EmployeeServiceImpl(@Qualifier("employeeDaoJdbcTemplate")EmployeeDao employeeDao){
    			this.employeeDao=employeeDao;
    }
    
	@Override
	public List<Employee> getAllEmployees() {
		System.out.println("Inside getAllEmployees method");
		return employeeDao.getAllEmp();
	}
	
	@Override
	public boolean addEmployee(Employee emp){
		return employeeDao.addEmp(emp);
	}

	@Override
	public boolean updateEmployee(Employee emp) {
		// TODO Auto-generated method stub
		return employeeDao.updateEmp(emp);
	}

	@Override
	public boolean deleteEmployee(int empId) {
		// TODO Auto-generated method stub
		return employeeDao.deleteEmp(empId);
	}

}
