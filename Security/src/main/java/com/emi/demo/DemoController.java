package com.emi.demo;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v2/demo")
public class DemoController {

	@GetMapping("/demo1")
	public ResponseEntity<String> sayHi(){
		return ResponseEntity.ok("hello from me ");
	}
}
