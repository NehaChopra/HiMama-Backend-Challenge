package com.himama.daycare.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import com.himama.daycare.domain.jpa.User;
import com.himama.daycare.domain.model.UserDetailsConfig;
import com.himama.daycare.exceptions.AuthExCode;
import com.himama.daycare.exceptions.AuthException;
import com.himama.daycare.repository.jpa.UserRepository;
import com.himama.daycare.service.CustomUserDetailsService;
import com.himama.daycare.util.UserFactory;

/**
 * 
 * @author nchopra
 *
 */
@Service
public class CustomUserDetailsServiceImpl implements CustomUserDetailsService {
	private static final Logger LOGGER = LoggerFactory.getLogger(CustomUserDetailsServiceImpl.class);

	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDetailsConfig loadUserByUsername(String username) throws AuthException {
		User user = userRepository.findByEmail(username);

		if (ObjectUtils.isEmpty(user)) {
			throw new AuthException(AuthExCode.EMAIL_NOT_FOUND.getCode(), AuthExCode.EMAIL_NOT_FOUND.getMessage());
		} else {
			return UserFactory.create(user);
		}
	}
	
	@Override
	public UserDetailsConfig loadUserByEmail(String email) throws UsernameNotFoundException {
		User user = userRepository.findByEmail(email);

		if (ObjectUtils.isEmpty(user)) {
			throw new AuthException(AuthExCode.EMAIL_NOT_FOUND.getCode(), AuthExCode.EMAIL_NOT_FOUND.getMessage());
		} else {
			return UserFactory.create(user);
		}
	}

}
