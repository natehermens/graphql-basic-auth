package com.gqlauth.security;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;

public class UserPrincipal implements UserDetails {

	private static final long serialVersionUID = 2884856135863517098L;
	
	private Account account;
	
	public UserPrincipal(Account account) {
		this.account = account;
	}

	@Override
	public Collection <? extends GrantedAuthority> getAuthorities() {
		String[] roles = account.getRoles().toArray(new String[0]);		
        return AuthorityUtils.createAuthorityList(roles);
    }

	@Override
	public String getPassword() {
		return account.getPassword();
	}

	@Override
	public String getUsername() {
		return account.getUsername();
	}

	@Override
	public boolean isAccountNonExpired() {
		return account.getActive();
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return account.getActive();
	}
}
