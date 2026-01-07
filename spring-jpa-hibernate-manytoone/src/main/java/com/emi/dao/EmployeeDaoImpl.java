package com.emi.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.emi.dto.Department;
import com.emi.dto.Employee;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import lombok.NoArgsConstructor;

//.getResultList is for only getting multiple records
//use .executeupdate for insert , update , delete
//use TypedQuery<T> if u know type of object to returned from the database

@NoArgsConstructor
@Repository
public class EmployeeDaoImpl implements EmployeeDao {

	@PersistenceContext
	EntityManager em;
	
	@Override
	public List<Employee> getAllEmpByDeptId(int deptId) {
		String jpql="select e from Employee e where e.department.deptId=:deptId";
		
		TypedQuery<Employee> empList=em.createQuery(jpql,Employee.class);
		empList.setParameter("deptId" , deptId);
		return empList.getResultList();
	}

	@Override
	public boolean addEmp(Employee employee, int deptId) {
		Department dept=em.find(Department.class,deptId);
		
		if(dept!=null) {
			employee.setDepartment(dept);
			em.persist(employee);
			return true;
		}else {
			return false;
		}
	}

	@Override
	public boolean addDep(Department deptartment) {
		em.persist(deptartment);
		return true;
	}

	@Override
	public List<Department> getAllDep() {
		TypedQuery<Department> depList=em.createQuery("select d from Department d",Department.class);
		return depList.getResultList();
	}

	
}
