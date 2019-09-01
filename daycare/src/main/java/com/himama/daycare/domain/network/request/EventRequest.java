package com.himama.daycare.domain.network.request;

import java.io.Serializable;

/**
 * @author nchopra
 */
public class EventRequest implements Serializable {

	private static final long serialVersionUID = -253207988057358728L;

	private String eventType;
	private String eventTimestamp;

	public EventRequest() {
		super();
	}

	public EventRequest(String eventType, String eventTimestamp) {
		this.eventType = eventType;
		this.eventTimestamp = eventTimestamp;
	}

	public String getEventType() {
		return eventType;
	}

	public void setEventType(String eventType) {
		this.eventType = eventType;
	}

	public String getEventTimestamp() {
		return eventTimestamp;
	}

	public void setEventTimestamp(String eventTimestamp) {
		this.eventTimestamp = eventTimestamp;
	}

}
