package com.himama.daycare.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.himama.daycare.domain.jpa.Event;
import com.himama.daycare.exceptions.AuthException;
import com.himama.daycare.service.EventDetailsService;

/**
 * 
 * @author nchopra
 *
 */
@RestController
public class UserDetailsController {
	
	@Autowired
	private EventDetailsService eventDetailsService;
	
	@RequestMapping(value = "/logged-events", method = RequestMethod.GET)
	public ResponseEntity<?> logEvent() throws AuthException {
		List<Event> events = eventDetailsService.listLoggedEvents();
		return ResponseEntity.ok(events);
	}
	
	@RequestMapping(value = "/logged-events", method = RequestMethod.POST)
	public ResponseEntity<?> createLogEvent(@RequestBody Event eventRequest) throws AuthException {
		Event event = eventDetailsService.createLoggedEvent(eventRequest);
		return ResponseEntity.ok(event);
	}
	
	@RequestMapping(value = "/logged-events", method = RequestMethod.PUT)
	public ResponseEntity<?> editLogEvent(@RequestBody Event eventRequest) throws AuthException {
		Event event = eventDetailsService.editLoggedEvent(eventRequest);
		return ResponseEntity.ok(event);
	}
	
	@RequestMapping(value = "/logged-events/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<?> deleteLogEvent(@PathVariable(required = true) Long id) throws AuthException {
		Boolean eventStatus = eventDetailsService.delete(id);
		return ResponseEntity.ok(eventStatus);
	}
}
