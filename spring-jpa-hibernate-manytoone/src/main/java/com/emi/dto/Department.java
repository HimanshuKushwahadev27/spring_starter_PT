package com.emi.dto;

import jakarta.persistence.Entity;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@XmlRootElement
public class Department {

	@jakarta.persistence.Id
	private int deptId;
	private String deptName;
}
