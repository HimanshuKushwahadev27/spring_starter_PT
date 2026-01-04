package com.emi.Api;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.emi.dto.Employee;
import com.emi.service.EmployeeService;

@RestController //recieves and handles the http requests
@RequestMapping("/employees") //maps the http requests to /employees URL
public class EmployeeApi {
   
	EmployeeService employeeService;
	
	public EmployeeApi(EmployeeService employeeService) {
		this.employeeService = employeeService;
	}
	
	
	@PostMapping(value="/addEmp" , produces={"application/json" , "application/xml"}, consumes={"application/xml" , "application/json"})
	public ResponseEntity<String> addEmployee(@RequestBody Employee emp){
		if(employeeService.addEmployee(emp)) {
			return ResponseEntity.ok("Employee added successfully");
		}else {
			return ResponseEntity.status(500).body("Failed to add employee");
		}
	}
	
	@GetMapping(value="/getAllEmp" , produces= {"application/json" , "application/xml"})
	public List<Employee> getEmployees(){
		return employeeService.getAllEmployees();
	}
	
	
	@GetMapping(value="/getById/{id}" , produces= {"application/json" , "application/xml"})
	public ResponseEntity<String> getById(@PathVariable int id){
		if(employeeService.getEmployeeById(id)) {
			return ResponseEntity.ok("Employee exists with id: " + id);
		}
		else {
			return ResponseEntity.status(404).body("Employee not found with id: " + id);
		}
	}
	
	@PutMapping(value="/updateEmp/{id}" , produces= {"application/json" , "application/xml"}, consumes= {"application/json" , "application/xml"})	
	public ResponseEntity<String> updateEmployee(@PathVariable int id , @RequestBody Employee emp){
		
		if(employeeService.updateEmployee(id,emp)) {
			return ResponseEntity.ok("Employee updated successfully");
		}else {
			return ResponseEntity.status(404).body("Employee not found with id: " + id);
		}
	}
	
}
