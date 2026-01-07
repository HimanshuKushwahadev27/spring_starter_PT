package com.emi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.emi.dao.EmployeeDao;
import com.emi.dto.Department;
import com.emi.dto.Employee;

import jakarta.transaction.Transactional;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Service
@Transactional
public class EmployeeServiceImpl implements EmployeeService {

	EmployeeDao employeeDao;
	
	@Autowired
	EmployeeServiceImpl(EmployeeDao employeeDao){
		this.employeeDao=employeeDao;
	}
	@Override
	public List<Employee> getAllEmployeesByDeptId(int deptId) {
		return employeeDao.getAllEmpByDeptId(deptId);
	}

	@Override
	public boolean addEmployee(Employee employee, int deptId) {
		return employeeDao.addEmp(employee, deptId);
	}

	@Override
	public boolean addDepartment(Department deptartment) {
		return employeeDao.addDep(deptartment);
	}

	@Override
	public List<Department> getAllDepartments() {
		return employeeDao.getAllDep();
	}

}
