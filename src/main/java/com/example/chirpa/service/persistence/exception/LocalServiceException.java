package com.example.chirpa.service.persistence.exception;


import com.example.chirpa.service.remote.ExceptionModel;

public class LocalServiceException extends Exception {
	private static final long serialVersionUID = 1L;

	private ExceptionModel model;

	public LocalServiceException() {
	}

	public LocalServiceException(String message) {
		super(message);
	}

	public LocalServiceException(Throwable th) {
		super(th);
	}

	public LocalServiceException(String message, Throwable th) {
		super(message, th);
	}

	public LocalServiceException(ExceptionModel model) {
		this.model = model;
	}
}
