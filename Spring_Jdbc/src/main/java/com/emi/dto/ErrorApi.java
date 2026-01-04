package com.emi.dto;

import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
@XmlRootElement
public class ErrorApi {

	private String errorCode;
	private String errorMessage;	
	
}
