package com.example.chirpa.service.remote;


public enum ExceptionCode {
	  GENERAL("General Exception")
	, UNSUPPORTED_OPERATION("The service has not been implemented yet.");

	private String userMessage;

	ExceptionCode(String userMessage) {
		this.userMessage = userMessage;
	}

	public String getUserMessage() {
		return userMessage;
	}

	public void setUserMessage(String userMessage) {
		this.userMessage = userMessage;
	}

}
