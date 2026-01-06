package com.emi.dao;

import java.util.List;

import org.springframework.stereotype.Component;

import com.emi.dto.Department;
import com.emi.dto.Employee;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Component
public class EmployeeDaoImpl implements EmployeeDao {

	@PersistenceContext
	EntityManager em;
	
	@Override
	public List<Employee> getAllEmployeeByDepartmentId(int deptId) {
		Department dept=em.find(Department.class,deptId);
		
		if(dept!=null) {
			return dept.getEmployees();
		}else {
			return null;
		}
	}

	@Override
	public boolean addEmployee(Employee emp, int deptId) {
		Department dept=em.find(Department.class , deptId);
		
		if(dept!=null) {
			List<Employee> empList=dept.getEmployees();
			empList.add(emp);
			return true;
		}else {
		   return false;
		}
	}

	@Override
	public boolean addDepartment(Department dept) {
		em.persist(dept);
		return true;
	}

	@Override
	public boolean getEmployeeById(int empId) {
		// TODO Auto-generated method stub
		return false;
	}

}
