 package com.emi.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.emi.config.DBConnection;
import com.emi.dto.Employee;

@Component
public class EmployeeDaoImpl implements EmployeeDao {
    
	
	DBConnection dbconnection;
	
    EmployeeDaoImpl(){}
    
    @Autowired
	EmployeeDaoImpl(DBConnection dbconnection){
		this.dbconnection=dbconnection;
	}
	
	@Override
	public List<Employee> getAllEmp() {
		String sql ="SELECT * FROM employee";
		
		List<Employee> empList=new ArrayList<>();
		
		try(
		//try with resources
		Connection conn=dbconnection.getConnectedWithPg();
		PreparedStatement pstmt=conn.prepareStatement(sql);){
			
			ResultSet rs=pstmt.executeQuery();
			while(rs.next()) {
				Employee emp=new Employee();
				emp.setEmpId(rs.getInt("EmpId"));
				emp.setEmpName(rs.getString("EmpName"));
				emp.setAddress(rs.getString("address"));
				emp.setSalary(rs.getFloat("salary"));
				
				empList.add(emp);
			}
			
			
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return empList;
	}

	@Override
	public boolean addEmp(Employee emp) {
		
		String sql="INSERT INTO employee (EmpId,EmpName,address,salary) VALUES(?,?,?,?)";
		try (
			// Perform database operations using the connection
			Connection conn = dbconnection.getConnectedWithPg();
		    PreparedStatement pstmt = conn.prepareStatement(sql);){
			
			System.out.println("Database connection established: " + conn);
			
			// Set parameters for the prepared statement
			pstmt.setInt(1, emp.getEmpId());
			pstmt.setString(2, emp.getEmpName());
			pstmt.setString(3, emp.getAddress());
			pstmt.setFloat(4, emp.getSalary());
			
			// Execute the insert operation
			int rowsAffect= pstmt.executeUpdate();
			return rowsAffect >	0;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean updateEmp(Employee emp) {
		//EmpId is primary key used to identify the employee to be updated
		String sql="UPDATE employee SET EmpName=? , address=? , salary=? WHERE EmpId=?";
		
		try(Connection con=dbconnection.getConnectedWithPg();
			PreparedStatement ptstmt=con.prepareStatement(sql);){
			
			ptstmt.setString(1, emp.getEmpName());
			ptstmt.setString(2, emp.getAddress());
			ptstmt.setFloat(3, emp.getSalary());
			ptstmt.setInt(4, emp.getEmpId());
			
			int row=ptstmt.executeUpdate();
			
			return row > 0 ;
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean deleteEmp(int empId) {
		String sql="DELETE FROM employee WHERE EmpId=?";
		
		try(Connection conn=dbconnection.getConnectedWithPg();
			PreparedStatement pstmt=conn.prepareStatement(sql);){
			
			//binds ? in sql with empId value
			pstmt.setInt(1, empId);
			int row=pstmt.executeUpdate();
			
			return row > 0 ;
		}catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

}
