package com.himama.daycare.domain.model;

import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.himama.daycare.domain.jpa.Event;

/**
 * 
 * @author nchopra
 *
 */
public class UserDetailsConfig implements UserDetails {

	private static final long serialVersionUID = 3813633038335770477L;

	private final Long id;
	private final String username;
	private final String password;
	private final String email;
	private final boolean enabled;
	private final Date lastPasswordSetOn;
	private List<Event> events;

	public UserDetailsConfig(Long id, String username, String email, String password, boolean enabled,
			Date lastPasswordSetOn, List<Event> events) {
		this.id = id;
		this.username = username;
		this.email = email;
		this.password = password;
		this.enabled = enabled;
		this.lastPasswordSetOn = lastPasswordSetOn;
		this.events = events;
	}

	@JsonIgnore
	public Long getId() {
		return id;
	}

	@Override
	public String getUsername() {
		return username;
	}

	@JsonIgnore
	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@JsonIgnore
	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@JsonIgnore
	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	public String getEmail() {
		return email;
	}

	@JsonIgnore
	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public boolean isEnabled() {
		return enabled;
	}

	@JsonIgnore
	public Date getLastPasswordSetOn() {
		return lastPasswordSetOn;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return null;
	}

	public List<Event> getEvents() {
		return events;
	}

	public void setEvents(List<Event> events) {
		this.events = events;
	}

}