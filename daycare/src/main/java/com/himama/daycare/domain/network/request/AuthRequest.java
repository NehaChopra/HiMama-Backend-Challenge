package com.himama.daycare.domain.network.request;

import java.io.Serializable;

/**
 * @author nchopra
 */
public class AuthRequest implements Serializable{

	private static final long serialVersionUID = -253207988057358728L;

	private String email;
	private String password;

	public AuthRequest() {
		super();
	}

	public AuthRequest(String email, String password) {
		this.email = email;
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
