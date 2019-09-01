package com.himama.daycare.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.himama.daycare.domain.jpa.Event;
import com.himama.daycare.exceptions.ApiException;

/**
 * 
 * @author nchopra
 *
 */
public interface EventDetailsService {

	List<Event> listLoggedEvents() throws ApiException;

	@Transactional
	Event createLoggedEvent(Event event) throws ApiException;

	@Transactional
	boolean delete(Long eventId) throws ApiException;

	@Transactional
	Event editLoggedEvent(Event event) throws ApiException;

}
