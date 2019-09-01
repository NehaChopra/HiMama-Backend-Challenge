package com.himama.daycare.controller;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.himama.daycare.domain.model.UserDetailsConfig;
import com.himama.daycare.domain.network.request.AuthRequest;
import com.himama.daycare.domain.network.response.AuthResponse;
import com.himama.daycare.exceptions.AuthExCode;
import com.himama.daycare.exceptions.AuthException;
import com.himama.daycare.service.CustomUserDetailsService;

/**
 * 
 * @author nchopra
 *
 */
@RestController
public class UserLoginController {

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private CustomUserDetailsService customUserDetailsService;

	@RequestMapping(value = "/auth", method = RequestMethod.POST)
	public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthRequest authRequest) throws AuthException {
		AuthResponse authResp = new AuthResponse();
		try {
			String email = authRequest.getEmail();
			String password = authRequest.getPassword();
			if (StringUtils.isBlank(email) || StringUtils.isBlank(password)) {
				throw new BadCredentialsException(AuthExCode.BAD_CREDDNETIALS.getMessage());
			}
			authenticate(email, password);
			final UserDetailsConfig userDetails = customUserDetailsService.loadUserByEmail(email);
			authResp.setUserDetails(userDetails);
			authResp.setStatus(1);
		} catch (DisabledException e) {
			throw new AuthException(AuthExCode.USER_DIASABLED.getCode(), AuthExCode.USER_DIASABLED.getMessage());
		} catch (BadCredentialsException e) {
			throw new AuthException(AuthExCode.BAD_CREDDNETIALS.getCode(), AuthExCode.BAD_CREDDNETIALS.getMessage());
		} catch (UsernameNotFoundException e) {
			throw new AuthException(AuthExCode.EMAIL_NOT_FOUND.getCode(), AuthExCode.EMAIL_NOT_FOUND.getMessage());
		}
		return ResponseEntity.ok(authResp);
	}

	private void authenticate(String email, String password) throws AuthException {

		authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, password));
	}
}
