package com.himama.daycare.repository.jpa;

import org.springframework.data.jpa.repository.JpaRepository;

import com.himama.daycare.domain.jpa.Event;

/**
 * 
 * @author nchopra
 *
 */
public interface EventRepository extends JpaRepository<Event, Long> {
	

}