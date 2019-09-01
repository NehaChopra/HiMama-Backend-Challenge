package com.himama.daycare.repository.jpa;

import org.springframework.data.jpa.repository.JpaRepository;

import com.himama.daycare.domain.jpa.User;

/**
 * 
 * @author nchopra
 *
 */
public interface UserRepository extends JpaRepository<User, Long> {
	User findByUsername(String username);

	User findByEmail(String email);
}