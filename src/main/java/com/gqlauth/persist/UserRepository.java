package com.gqlauth.persist;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.gqlauth.types.User;

public interface UserRepository  extends MongoRepository<User, String>{

	Optional<User> findByUsername(String username);
	
	List<User> findByUsernameLike(String username);
}
