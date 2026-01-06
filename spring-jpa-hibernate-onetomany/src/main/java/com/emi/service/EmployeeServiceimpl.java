package com.emi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.emi.dao.EmployeeDao;
import com.emi.dto.Department;
import com.emi.dto.Employee;

import jakarta.transaction.Transactional;

@Transactional
@Component
public class EmployeeServiceimpl implements EmployeeService {

	EmployeeDao employeeDao;
	
	EmployeeServiceimpl(){}
	
	@Autowired
	EmployeeServiceimpl(EmployeeDao employeeDao){
		this.employeeDao=employeeDao;
	}
	
	@Override
	public List<Employee> getAllEmpByDeptId(int deptId) {
		return employeeDao.getAllEmployeeByDepartmentId(deptId);
	}

	@Override
	public boolean addEmp(Employee emp, int deptId) {
		return employeeDao.addEmployee(emp, deptId);
	}

	@Override
	public boolean addDept(Department dept) {
		return employeeDao.addDepartment(dept);
	}

	@Override
	public boolean getEmpById(int empId) {
		return employeeDao.getEmployeeById(empId);
	}

}
