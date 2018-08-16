package com.gqlauth.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.gqlauth.persist.Mutation;
import com.gqlauth.persist.Query;
import com.gqlauth.persist.UserDao;
import com.gqlauth.persist.UserRepository;

@Configuration
public class GraphQLConfiguration {

	@Autowired
	private UserRepository userRepo;
	
	@Bean
	public UserDao userDao() {
		return new UserDao(userRepo);
	}
	
	@Bean
	public Query query(UserDao userDao) {
		return new Query(userDao);
	}
	
	@Bean
	public Mutation mutation(UserDao userDao) {
		return new Mutation(userDao);
	}
}
