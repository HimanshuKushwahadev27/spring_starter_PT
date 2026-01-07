package com.emi.api;

import java.util.List;

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

	
	EmployeeService employeeService;
	
	EmployeeApi(EmployeeService employeeService){
		this.employeeService=employeeService;
	}
	
	@GetMapping(value="/getEmp/{deptId}" , produces= {"application/json" , "application/xml"})
	public List<Employee> getAllEmployeeByDeptId(@PathVariable int deptId){
		List<Employee> empList=employeeService.getAllEmployeesByDeptId(deptId);
		if(empList!=null) {
			return empList;
		}else {
			return null;
		}
	}
	
	@PostMapping(value="/addEmp/{deptId}" ,
			
			    produces= {"application/json" , "application/xml"} ,
			    consumes = {"application/json" , "application/xml"})
	
	public ResponseEntity<String> addEmp(@RequestBody Employee emp , @PathVariable int deptId) {
		if(employeeService.addEmployee(emp, deptId)) {
			return  ResponseEntity.ok("Employee addded");
		}else {
			return ResponseEntity.badRequest().body("Employee not added");
		}
	}
	
	@PostMapping(value="/addDept" , produces= {"application/json" , "application/xml"} , consumes= {"application/json" , "application/xml"})
	public ResponseEntity<String> addDept(@RequestBody Department dept){
		if(employeeService.addDepartment(dept)) {
			return  ResponseEntity.ok("Department added");
		}else {
			return ResponseEntity.badRequest().body("Department not added");

		}
	}
	
	@GetMapping(value="/getDept" ,produces= {"application/json" , "application/xml"} )
	public List<Department> getDept(){
		List<Department> depList=employeeService.getAllDepartments();
		if(depList!=null) {
			return depList;
		}else{
			return null;
		}
		
	}
}
