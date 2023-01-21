package com.example.BlogApp.BlogServiceimpl.PostException;

import org.springframework.http.HttpStatus;

public class BlogAPIException extends RuntimeException{
	
	private HttpStatus status;
	private String msg;
	public BlogAPIException(HttpStatus status, String msg) {
		super("Comment Does not belongs to post"
				+ "");
		this.status = status;
		this.msg = msg;
	}
	public HttpStatus getStatus() {
		return status;
	}
	public void setStatus(HttpStatus status) {
		this.status = status;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	


}
