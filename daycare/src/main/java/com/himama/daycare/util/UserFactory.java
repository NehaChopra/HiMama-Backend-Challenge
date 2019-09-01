package com.himama.daycare.util;

import com.himama.daycare.domain.jpa.User;
import com.himama.daycare.domain.model.UserDetailsConfig;

public final class UserFactory {

	private UserFactory() {
	}

	public static UserDetailsConfig create(User user) {
		return new UserDetailsConfig(user.getId(), user.getUsername(), user.getEmail(), user.getPassword(),
				user.getEnabled(), user.getLastPasswordSetOn(), user.getEvents());
	}
}
