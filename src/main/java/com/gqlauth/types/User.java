package com.gqlauth.types;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Field;

import com.gqlauth.security.Account;


public class User implements Account {
	
	@Id 
	private String id;
	// login information
	@Field
	private String username;
	@Field
	private String password;
	@Field
	private List<String> roles = new ArrayList<>();
	@Field
	private String email;
	@Field
	private Boolean active = true;

	@Override
	public String getId() { return id; }
	@Override
	public User setId(String id) { 
		this.id = id;
		return this;
	}
	
	@Override
	public String getUsername() { return username; }
	@Override
	public User setUsername(String username) {
		this.username = username;
		return this;
	}
	@Override
	public String getPassword() { return password; }
	@Override
	public User setPassword(String hash) {
		this.password = hash;
		return this;
	}
    @Override
    public List<String> getRoles() { return roles != null && roles.size() > 0 ? roles : Arrays.asList("USER"); }
    @Override
    public User setRoles(String...roles) {
        this.roles = Arrays.asList(roles);
        return this;
    }
	public User addRole(String role) {
		if(!roles.contains(role)) {
			roles.add(role);
		}
		return this;
	}
	public boolean hasRole(String role) {
		return roles.contains(role);
	}
	public Account removeRole(String role) {
		roles.remove(role);
		return this;
	}

	public String getEmail() { return email; }
	public User setEmail(String email) {
		this.email = email;
		return this;
	}
	
	public boolean getActive() { return active; }
	public User setActive(Boolean active) {
		this.active = active;
		return this;
	}

}
