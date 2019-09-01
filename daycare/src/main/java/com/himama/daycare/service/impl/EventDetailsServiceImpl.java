package com.himama.daycare.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.validation.ConstraintViolationException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;

import com.himama.daycare.domain.jpa.Event;
import com.himama.daycare.exceptions.ApiExCode;
import com.himama.daycare.exceptions.ApiException;
import com.himama.daycare.repository.jpa.EventRepository;
import com.himama.daycare.service.EventDetailsService;
import com.himama.daycare.util.FillNonEmptyBeanUtils;

/**
 * 
 * @author nchopra
 *
 */
@Service
public class EventDetailsServiceImpl implements EventDetailsService {
	private static final Logger LOGGER = LoggerFactory.getLogger(EventDetailsServiceImpl.class);

	@Autowired
	private EventRepository eventRepository;

	@Override
	public List<Event> listLoggedEvents() throws ApiException {
		List<Event> events = eventRepository.findAll();
		if (CollectionUtils.isEmpty(events)) {
			LOGGER.error("Error while reading a list of events");
			throw new ApiException(ApiExCode.RECORD_NOT_FOUND.getCode(), ApiExCode.RECORD_NOT_FOUND.getMessage());
		}
		return events;
	}

	@Override
	public Event createLoggedEvent(Event event) throws ApiException {
		try {
			if (!ObjectUtils.isEmpty(event)) {
				return eventRepository.save(event);
			}
		} catch (ConstraintViolationException ex) {
			if(!ObjectUtils.isEmpty(ex.getConstraintViolations()))
				throw new ApiException(ApiExCode.NOT_VALID_RECORD.getCode(), ex.getConstraintViolations().stream().map((e) -> e.getMessage()).collect(Collectors.toList()));
		} catch (Exception e) {
			LOGGER.error("Error while creating an events");
			throw new ApiException(ApiExCode.NOT_VALID_RECORD.getCode(), ApiExCode.NOT_VALID_RECORD.getMessage());
		}
		return null;
	}

	@Override
	public boolean delete(Long eventId) throws ApiException {
		try {
			if (!ObjectUtils.isEmpty(eventRepository.findById(eventId))) {
				eventRepository.deleteById(eventId);
				return true;
			} else {
				LOGGER.error("Error while deleting an events");
				throw new ApiException(ApiExCode.RECORD_NOT_FOUND.getCode(), ApiExCode.RECORD_NOT_FOUND.getMessage());
			}
		} catch (Exception e) {
			LOGGER.error("Error while deleting a logged event");
		}
		return false;
	}

	@Override
	public Event editLoggedEvent(Event event) throws ApiException {
		try {
			Optional<Event> dbevent = eventRepository.findById(event.getId());
			if (!dbevent.isPresent() && !ObjectUtils.isEmpty(event)) {
				LOGGER.error("Error while reading a list of events");
				throw new ApiException(ApiExCode.RECORD_NOT_FOUND.getCode(), ApiExCode.RECORD_NOT_FOUND.getMessage());
			}
			FillNonEmptyBeanUtils emp = new FillNonEmptyBeanUtils();
			emp.copyProperties(dbevent, event);
			eventRepository.save(dbevent.get());
			return dbevent.get();
		} catch (ConstraintViolationException ex) {
			if(!ObjectUtils.isEmpty(ex.getConstraintViolations()))
				throw new ApiException(ApiExCode.NOT_VALID_RECORD.getCode(), ex.getConstraintViolations().stream().map((e) -> e.getMessage()).collect(Collectors.toList()));
		} catch (Exception e) {
			LOGGER.error("Error in editing the LoggedEvent");
		}
		return null;
	}
}
