package com.employee;

import org.springframework.http.HttpStatus;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class Responses {

	private Object data;
	private String message;
	private String status;
	@JsonIgnore
	private HttpStatus httpStatus;
	
	public Responses() {
		// TODO Auto-generated constructor stub
	}
	
	public Responses(Object data, String message, String status, HttpStatus httpStatus) {
		super();
		this.data = data;
		this.message = message;
		this.status = status;
		this.httpStatus = httpStatus;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public HttpStatus getHttpStatus() {
		return httpStatus;
	}

	public void setHttpStatus(HttpStatus httpStatus) {
		this.httpStatus = httpStatus;
	}
	
}
