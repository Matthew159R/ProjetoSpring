package com.example.demo.services.exceptions;

public class DatabaseException extends RuntimeException {
	private static final long serialVersionUID = 021120231751L;
	
	public DatabaseException (String msg)
	{
		super(msg);
		
	}
}
