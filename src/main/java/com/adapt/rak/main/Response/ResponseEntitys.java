package com.adapt.rak.main.Response;

public class ResponseEntitys<T> {
	private int status;
	private String message;
	private String description;
	private T data;
	
	
	public ResponseEntitys(int status, String message, String description) {
		
		this.status = status;
		this.message = message;
		this.description = description;
	}
	public ResponseEntitys(int status, String message, String description, T data) {
		this.status = status;
		this.message = message;
		this.description = description;
		this.data = data;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public T getData() {
		return data;
	}
	public void setData(T data) {
		this.data = data;
	}
	
	

}
