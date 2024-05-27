package com.mergeproject.customexception;

public class CustomeException extends Exception {
   
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public CustomeException(String message, Exception e)
	{
		super(message);
	}
}
