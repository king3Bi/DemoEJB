package com.ejb.demo.model;

import com.google.gson.annotations.Expose;

public class Message {
	@Expose
	private int statusCode;
	
	@Expose
	private String message;
	
	public Message(int statusCode, String message) {
		super();
		this.statusCode = statusCode;
		this.message = message;
	}

	public int getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	@Override
	public String toString() {
		return "Message [statusCode=" + statusCode + ", message=" + message + "]";
	}
}
