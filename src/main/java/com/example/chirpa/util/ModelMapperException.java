package com.example.chirpa.util;

public class ModelMapperException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public ModelMapperException(){	
	}

	public ModelMapperException(String message){
		super(message);
	}
	
	public ModelMapperException(Throwable th){
		super(th);
	}
	
	public ModelMapperException(String message, Throwable th){
		super(message, th);
	}
}
