package com.emi.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.emi.dto.Department;
import com.emi.dto.Employee;
import com.emi.service.EmployeeService;

@RestController
@RequestMapping("/employees")
public class EmployeeApi {

	private EmployeeService employeeService;
	
	EmployeeApi(){}
	
	@Autowired
	EmployeeApi(EmployeeService employeeService){
		this.employeeService=employeeService;
	}
	
	@PostMapping(value="/addEmp/{deptId}" ,consumes= {"application/json" , "application/xml"}  , produces= {"application/json","appliaction/xml"})
	public  ResponseEntity<String> addEmployee(@PathVariable int deptId, @RequestBody Employee emp){
		
		if(employeeService.addEmp(emp,deptId)) {
			return ResponseEntity.ok("Employee added successfully");
		}else {
			return ResponseEntity.badRequest().body("Failed to add employee");
		}
	}
	
	@PostMapping(value="/addDept" ,consumes= {"application/json" , "application/xml"}  , produces= {"application/json","appliaction/xml"})
	public ResponseEntity<String> addDepartment(@RequestBody Department dept){
		if(employeeService.addDept(dept)) {
			return ResponseEntity.ok("Department added successfully");
		}else {
			return ResponseEntity.badRequest().body("Failed to add department");
		}
		
	}
	
	@GetMapping(value="/getAllByDeptId/{deptId}" , produces= {"application/json" , "appication/xml"})
	public List<Employee> getAllEmployeesBYDepartmentId(@PathVariable int deptId){
		List<Employee> empList=employeeService.getAllEmpByDeptId(deptId);
		
		if(empList!=null) {
			return empList;
		}else {
			throw new RuntimeException("Department with id "+deptId+" not found");
		}
	}
}
