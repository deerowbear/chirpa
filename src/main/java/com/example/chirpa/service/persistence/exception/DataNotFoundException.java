package com.example.chirpa.service.persistence.exception;

public class DataNotFoundException extends Exception {
	private static final long serialVersionUID = 1L;

	public DataNotFoundException(){	
	}

	public DataNotFoundException(String message){
		super(message);
	}
	
	public DataNotFoundException(Throwable th){
		super(th);
	}
	
	public DataNotFoundException(String message, Throwable th){
		super(message, th);
	}
}
