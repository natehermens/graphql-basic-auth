package com.gqlauth.persist;

import java.util.List;

import org.springframework.security.access.prepost.PostFilter;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.gqlauth.types.User;

public class Query implements GraphQLQueryResolver {

	private UserDao userDao;
	
	public Query(UserDao userDao) {
		this.userDao = userDao;
	}
	
	@PostFilter("filterObject.username == principal.username || hasRole('ROLE_ADMIN')")
	public List<User> allUsers() {
		return userDao.getAll();
	}
}
