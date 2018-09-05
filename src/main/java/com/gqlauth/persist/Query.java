package com.gqlauth.persist;

import java.util.List;

import org.springframework.security.access.prepost.PostFilter;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.access.prepost.PreFilter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.gqlauth.types.User;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Query implements GraphQLQueryResolver {

	private UserDao userDao;
	
	public Query(UserDao userDao) {
		this.userDao = userDao;
	}
	
	@PostFilter("filterObject.username == authentication.name || hasRole('ROLE_ADMIN')")
	public List<User> allUsers() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		log.debug("auth name: "+auth.getName());
		return userDao.getAll();
	}
}
