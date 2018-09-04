package com.gqlauth.persist;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import com.gqlauth.payload.AuthData;
import com.gqlauth.payload.JwtAuthenticationResponse;
import com.gqlauth.security.JwtTokenProvider;
import com.gqlauth.types.User;

public class Mutation implements GraphQLMutationResolver {

	private static Logger LOGGER = LoggerFactory.getLogger(Mutation.class);
	
	private UserDao userDao;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
    private JwtTokenProvider tokenProvider;
	
	@Autowired
    AuthenticationManager authenticationManager;
	
	
	public Mutation(UserDao userDao) {
		this.userDao = userDao;
	}
	
	public User createUser(String email, AuthData auth) {
		User newUser = null;
		LOGGER.info("attempting to create user: "+auth.getUsername()+" with email: "+email);
		if(!userDao.getByUsername(auth.getUsername()).isPresent()) {
			newUser = userDao.saveUser(new User()
							.setUsername(auth.getUsername())
							.setPassword(passwordEncoder.encode(auth.getPassword()))
							.setEmail(email)
							.setRoles("ROLE_USER")
							.setActive(true));
		}
		
		return newUser;
	}
	
	public JwtAuthenticationResponse signinUser(AuthData auth) throws IllegalAccessException {

	    Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                		auth.getUsername(),
                		auth.getPassword()
                )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = tokenProvider.generateToken(authentication);
        
        return new JwtAuthenticationResponse(jwt);
	}
}
