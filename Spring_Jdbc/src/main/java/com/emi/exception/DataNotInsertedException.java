package com.emi.exception;

public class DataNotInsertedException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public DataNotInsertedException(String message) {
		super(message);
	}

}
