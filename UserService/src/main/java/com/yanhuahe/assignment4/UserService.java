package com.yanhuahe.assignment4;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	public User registerUser(User user) {
		return userRepository.save(user);
	}
	
	public Optional<User> findUserByUsername(String username) {
		return userRepository.findByUsername(username);
	}
	
	public boolean validateUser(String username, String password) {
		Optional<User> user = userRepository.findByUsername(username);
		return user.isPresent() && user.get().getPassword().equals(password);
	}

}
