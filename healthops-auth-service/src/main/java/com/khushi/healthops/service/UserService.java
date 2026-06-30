package com.khushi.healthops.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.khushi.healthops.model.User;
import com.khushi.healthops.repository.UserRepository;

@Service
public class UserService {

	private final UserRepository userRepository;
	
	public UserService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}
	
	public Optional<User> findByEmail(String email) {
		return userRepository.findByEmail(email);
	}
}
