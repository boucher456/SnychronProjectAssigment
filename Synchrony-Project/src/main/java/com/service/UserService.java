package com.service;

import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.Entity.User;
import com.reposistory.UserRepository;

@Service
public class UserService {
	private static final Logger logger = LoggerFactory.getLogger(UserService.class);
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private PasswordEncoder passwordEncoder;

	public User registerUser(User user) {

		logger.debug("Encoding password for user: {}", user.getUsername());
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		try {
			return userRepository.save(user);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		logger.debug("User saved: {}", user.getUsername());
		return user;
	}

	public Optional<User> findByUsername(String username) {
		logger.debug("Finding user by username: {}", username);
		Optional<User> user = userRepository.findByUsername(username);
		user.ifPresentOrElse(u -> logger.debug("User found: {}", username),
				() -> logger.warn("User not found: {}", username));
		return user;
	}
}