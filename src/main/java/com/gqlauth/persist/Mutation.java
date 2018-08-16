package com.gqlauth.persist;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import com.gqlauth.types.AuthData;
import com.gqlauth.types.User;

public class Mutation implements GraphQLMutationResolver {

	private UserDao userDao;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	public Mutation(UserDao userDao) {
		this.userDao = userDao;
	}
	
	public User createUser(String email, AuthData auth) {
		User newUser = null;
		if(userDao.getByUsername(auth.getUsername()) == null) {
			newUser = userDao.saveUser(new User()
							.setUsername(auth.getUsername())
							.setPassword(passwordEncoder.encode(auth.getPassword()))
							.setEmail(email)
							.setRoles("ROLE_USER")
							.setActive(true));
		}
		
		return newUser;
	}
}
