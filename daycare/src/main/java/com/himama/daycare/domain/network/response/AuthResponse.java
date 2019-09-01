package com.himama.daycare.domain.network.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.himama.daycare.domain.model.UserDetailsConfig;

/**
 * @author nchopra
 */
@JsonInclude(Include.NON_NULL)
public class AuthResponse extends BaseResponse {

	private static final long serialVersionUID = 2265990349149254247L;

	private UserDetailsConfig userDetails;

	public AuthResponse() {
	}

	public UserDetailsConfig getUserDetails() {
		return userDetails;
	}

	public void setUserDetails(UserDetailsConfig userDetails) {
		this.userDetails = userDetails;
	}

}
