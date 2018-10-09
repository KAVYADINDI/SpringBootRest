package com.capgemini.orderapp.entity;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;

public class ErrorMessage {

	private String errorMessage;
	private HttpStatus status;
	private LocalDateTime time;
	private String url;
	public ErrorMessage() {
		super();
	}
	public ErrorMessage(String errorMessage, HttpStatus status, LocalDateTime time, String url) {
		this();
		this.errorMessage = errorMessage;
		this.status = status;
		this.time = time;
		this.url = url;
	}
	public String getErrorMessage() {
		return errorMessage;
	}
	public HttpStatus getStatus() {
		return status;
	}
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	public void setStatus(HttpStatus status) {
		this.status = status;
	}
	public void setTime(LocalDateTime time) {
		this.time = time;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public LocalDateTime getTime() {
		return time;
	}
	public String getUrl() {
		return url;
	}
	@Override
	public String toString() {
		return "ErrorMessage [errorMessage=" + errorMessage + ", status=" + status + ", time=" + time + ", url=" + url
				+ "]";
	}
	
}
