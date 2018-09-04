package com.gqlauth.persist;

import java.util.List;
import java.util.stream.Collectors;

import java.util.Optional;
import com.gqlauth.types.User;

public class UserDao {

	private UserRepository userRepo;
	
	public UserDao(UserRepository repo) {
		userRepo = repo;
	}
	
	public List<User> getAll() {
		return userRepo.findAll()
					.stream()
					.map(u -> u.setPassword(""))					
					.collect(Collectors.toList());
	}
	
	public Optional<User> getByUserId(String id) {
		return userRepo.findById(id);
	}
	
	public Optional<User> getByUsername(String username) {
		return userRepo.findByUsername(username);
	}
	
	public User saveUser(User user) {
		
		User added = null;
		if(!userRepo.findByUsername(user.getUsername()).isPresent()) {
			added = userRepo.save(user);
		}
		return added;
	}

	
}
