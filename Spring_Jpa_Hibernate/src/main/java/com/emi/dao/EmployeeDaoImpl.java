package com.emi.dao;

import java.util.List;

import org.springframework.stereotype.Component;

import com.emi.dto.Employee;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

/// 

@Component("employeeDaoImpl")

public class EmployeeDaoImpl implements EmployeeDao {

	@PersistenceContext
	//jpaApi
	private EntityManager em;
	
	//signifies that the method should be executed within a transaction
	//if any exception occurs during the method execution, the transaction will be rolled back
	//spring automatically handles the aop(proxy) creation and transaction management
	@Transactional
	@Override
	//start
	public boolean addEmp(Employee emp) {
		em.persist(emp);
		return true;
	}//commit

	@Override
	public boolean updateEmp(Integer id ,Employee emp) {
		Employee existingEmp=em.find(Employee.class, id);
		
		if(existingEmp!=null) {
			existingEmp.setEmpName(emp.getEmpName());
			existingEmp.setSalary(emp.getSalary());
			existingEmp.setAddress(emp.getAddress());
			em.merge(existingEmp); //updating the existing entity
			return true;
		}else {
			return false;
		}
	}

	@Override
	public boolean deleteEmp(int empId) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<Employee> getAllEmp() {
		//if on the basis of place we want to filter the employees
		//use "select e from Employee e where e.address.city=:city" (:city is a placeholder)
		//use em.createQuery(jpql, Employee.class).setParameter("city", "args").getResultList();
		//and the rest is same
		String jpql="select e from Employee e";
		List<Employee> empList=em.createQuery(jpql, Employee.class).getResultList();
		
		return empList;
	}

	@Transactional
	@Override
	public boolean getEmpById(int empId) {
		///telling jpa to give employee with this primary key
		//EntityManager -> hibernate -> jdbc ->postgresql
	   Employee emp=em.find(Employee.class, empId);
	   return emp!=null;
	}

}
