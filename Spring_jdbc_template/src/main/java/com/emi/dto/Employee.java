package com.emi.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@XmlRootElement
@NoArgsConstructor
public class Employee {
  
   private int EmpId;
   //ensuring it isnt blank
   @NotBlank(message="Employee name cannot be blank")
   private String EmpName;
   @NotBlank(message="Address cannot be blank")
   private String address;
   @Min(value=10000 , message="salary must be greater than 10000")
   private float salary;


   
   
   
}
