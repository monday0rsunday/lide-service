package com.broduce.lide.model;

import java.io.Serializable;

public class Error implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 3984767275158810688L;
	private int code;
	private String message;

	public Error() {

	}

	public Error(int code, String message) {
		setCode(code);
		setMessage(message);
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}
}
