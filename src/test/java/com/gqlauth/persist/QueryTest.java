package com.gqlauth.persist;

import static org.junit.Assert.assertTrue;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.context.WebApplicationContext;

import com.gqlauth.security.JwtTokenProvider;
import com.gqlauth.security.UserPrincipal;
import com.gqlauth.testutil.GraphQLTestAuthTemplate;
import com.gqlauth.types.User;
import com.graphql.spring.boot.test.GraphQLResponse;
import com.graphql.spring.boot.test.GraphQLTest;


@RunWith(SpringRunner.class)
@GraphQLTest
@ComponentScan(basePackages= {"com.gqlauth"})
@DataMongoTest
public class QueryTest {

	@Autowired
	private UserDao userDao;
	
	@Autowired 
	private PasswordEncoder passwordEncoder;
	
	@Autowired
    protected WebApplicationContext webApplicationContext;
	
	@Autowired
	private GraphQLTestAuthTemplate graphQLTemplate;

	@Autowired
	AuthenticationManager authenticationManager;
	
	@Autowired
	private JwtTokenProvider jwtTokenProvider;
	
	@Test
	public void runQueryTestWithTemplate() throws Exception {

		List<User> newUsers = 
				Stream.of("user1","user2","user3")
					.map(u -> addUser(u, "password", u+"@email.com"))
					.collect(Collectors.toList());

		UserPrincipal user = new UserPrincipal(newUsers.get(0));

		UsernamePasswordAuthenticationToken authReq = new UsernamePasswordAuthenticationToken(user, "password");
		Authentication auth = authenticationManager.authenticate(authReq);
		
		String token = jwtTokenProvider.generateToken(auth);
		
		GraphQLResponse response = graphQLTemplate.postForResource(token, "graphql/get-all-users.graphql");
		
		assertTrue(response.isOk());

		System.out.println(response.getRawResponse().getBody());
	}
	
	private User addUser(String username, String password, String email) {
		return userDao.saveUser(
				new User()
					.setUsername(username)
					.setPassword(passwordEncoder.encode(password))
					.setEmail(email));
	}
}
