package com.example.chirpa.service.persistence.exception;

public class PersistenceException extends Exception {
	private static final long serialVersionUID = 1L;

	public PersistenceException(){	
	}

	public PersistenceException(String message){
		super(message);
	}
	
	public PersistenceException(Throwable th){
		super(th);
	}
	
	public PersistenceException(String message, Throwable th){
		super(message, th);
	}
}
