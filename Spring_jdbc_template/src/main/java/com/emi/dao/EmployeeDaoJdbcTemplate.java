package com.emi.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.emi.dto.Employee;

@Component
public class EmployeeDaoJdbcTemplate implements EmployeeDao {
    
	private JdbcTemplate jdbcTemplate;
	
	EmployeeDaoJdbcTemplate(){}
	
	@Autowired
	EmployeeDaoJdbcTemplate(JdbcTemplate jdbcTemplate){
		this.jdbcTemplate = jdbcTemplate;
	}
	
	@Override
	public List<Employee> getAllEmp() {
		List<Employee> emplist=jdbcTemplate.query("select * from employee", new BeanPropertyRowMapper<>(Employee.class));
		return emplist;
 	}

	@Override
	public boolean updateEmp(Employee emp) {
		int c=jdbcTemplate.update("update employee set EmpName=? , address=? , salary=? where Empid=?",
				           emp.getEmpName(),emp.getAddress(),emp.getSalary(),emp.getEmpId());
	   return c > 0;
	}

	@Override
	public boolean addEmp(Employee emp) {
		int c=jdbcTemplate.update("insert into employee (EmpId,EmpName,address,salary) values(?,?,?,?)", 
				           emp.getEmpId(),emp.getEmpName(),emp.getAddress(),emp.getSalary());
	   return c > 0;
	}

	@Override
	public boolean deleteEmp(int empId) {
		int c=jdbcTemplate.update("delete from employee where EmpId=?", empId);
	    return c > 0;
	}

}
