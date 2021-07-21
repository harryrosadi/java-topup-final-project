package com.tugasnegaraprojectjava.database.service;

import com.tugasnegaraprojectjava.database.model.User;
import org.springframework.stereotype.Service;


import java.io.IOException;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserForgotPassword {

	private static final long EXPIRE_TOKEN_AFTER_MINUTES = 30;
	MybatisUser mybatisUser = new MybatisUser();

	public String forgotPassword(String email) throws IOException {

		Optional<User> userOptional = Optional
				.ofNullable(mybatisUser.findByEmail(email));

		if (!userOptional.isPresent()) {
			return "Invalid email id.";
		}

		User user = userOptional.get();
		user.setToken(generateToken());

		mybatisUser.updateUser(user);

		return user.getToken();
	}

	public String resetPassword(String token, String password) throws IOException {

		Optional<User> userOptional = Optional
				.ofNullable(mybatisUser.findByToken(token));

		if (!userOptional.isPresent()) {
			return "Invalid token.";
		}


		User user = userOptional.get();

		user.setPassword(password);
		user.setToken(null);

		mybatisUser.updateUser(user);

		return "Your password successfully updated.";
	}

	/**
	 * Generate unique token. You may add multiple parameters to create a strong
	 * token.
	 * 
	 * @return unique token
	 */
	private String generateToken() {
		StringBuilder token = new StringBuilder();

		return token.append(UUID.randomUUID().toString())
				.append(UUID.randomUUID().toString()).toString();
	}

	/**
	 * Check whether the created token expired or not.
	 * 
	 * @param tokenCreationDate
	 * @return true or false
	 */
	private boolean isTokenExpired(final LocalDateTime tokenCreationDate) {

		LocalDateTime now = LocalDateTime.now();
		Duration diff = Duration.between(tokenCreationDate, now);

		return diff.toMinutes() >= EXPIRE_TOKEN_AFTER_MINUTES;
	}

}
