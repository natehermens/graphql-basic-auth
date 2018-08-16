package com.gqlauth.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.gqlauth.persist.UserDao;
import com.gqlauth.types.User;

@Component
public class UserDetailsServiceImpl implements UserDetailsService {
	
	@Autowired
	private UserDao userDao;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		User user = userDao.getByUsername(username);
		if(user == null) {
			throw new UsernameNotFoundException("No user with "+username+" found.");
		}
		
		return new UserPrincipal(user);
	}

}
