package com.example.demo.services.exceptions;

public class ResourceNotFoundException extends RuntimeException {
	private static final long serialVersionUID = 311020232354L;
	
	public ResourceNotFoundException (String msg)
	{
		super(msg);
		
	}
}
