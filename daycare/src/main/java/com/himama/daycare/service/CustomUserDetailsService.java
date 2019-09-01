package com.himama.daycare.service;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.himama.daycare.domain.model.UserDetailsConfig;

/**
 * 
 * @author nchopra
 *
 */
public interface CustomUserDetailsService extends UserDetailsService {

	public UserDetailsConfig loadUserByEmail(String email) throws UsernameNotFoundException;
}
