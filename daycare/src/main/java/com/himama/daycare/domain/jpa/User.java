package com.himama.daycare.domain.jpa;

import static javax.persistence.GenerationType.IDENTITY;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * 
 * @author nchopra
 *
 */
@Entity
@Table(name = "hm_user")
public class User implements Serializable {

	private static final long serialVersionUID = -1763574516474353414L;
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Long id;

	@Column(name = "username", length = 255)
	@NotNull(message = "username is required")
	@NotEmpty(message = "username is required")
	@Size(min = 4, max = 255)
	private String username;

	@Column(name = "password", length = 255)
	@NotNull(message = "password is required")
	@NotEmpty(message = "password is required")
	@Size(min = 4, max = 255)
	private String password;

	@Column(name = "phone", length = 50)
	@NotNull
	@Size(min = 4, max = 50)
	private String phone;

	@Column(name = "email", length = 255, unique = true)
	@Size(min = 4, max = 255)
	private String email;

	@Column(name = "enabled")
	@NotNull
	private Boolean enabled;

	@Column(name = "modified_on")
	@Temporal(TemporalType.TIMESTAMP)
	private Date modifiedOn;

	@Column(name = "created_on")
	@Temporal(TemporalType.TIMESTAMP)
	private Date createdOn;

	@Column(name = "last_password_set_on")
	@Temporal(TemporalType.TIMESTAMP)
	private Date lastPasswordSetOn;

	@OneToMany(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_Id", referencedColumnName = "id")
	private List<Event> events;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Boolean getEnabled() {
		return enabled;
	}

	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}

	public Date getModifiedOn() {
		return modifiedOn;
	}

	public void setModifiedOn(Date modifiedOn) {
		this.modifiedOn = modifiedOn;
	}

	public Date getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}

	public Date getLastPasswordSetOn() {
		return lastPasswordSetOn;
	}

	public void setLastPasswordSetOn(Date lastPasswordSetOn) {
		this.lastPasswordSetOn = lastPasswordSetOn;
	}

	public List<Event> getEvents() {
		return events;
	}

	public void setEvents(List<Event> events) {
		this.events = events;
	}

}
