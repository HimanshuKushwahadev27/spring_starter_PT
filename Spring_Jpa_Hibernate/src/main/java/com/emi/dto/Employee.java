package com.emi.dto;

import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//lifecycle of entity is managed by JPA

//  entity creation (transient) -> persist(managed , all the changes made 
                         //are stored in the database) ->detach(commit ,or em.clear() ,em.close()
                        // changes made are not stored) -> removed(delete)
@Data
@AllArgsConstructor
@XmlRootElement
@NoArgsConstructor
@Entity
@Table(name="Employee_Table")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer EmpId;
    
    @XmlElement(name="empname")
	private String EmpName;
	@Embedded
	private Address address;
	private float salary;
}
