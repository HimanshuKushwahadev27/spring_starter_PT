package com.emi.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
@XmlRootElement
public class Employee {
  
private int EmpId;
   //ensuring it isnt blank
   @NotBlank(message="Employee name cannot be blank")
   private String EmpName;
   
   private String address;
   private float salary;


   
   
   
}
