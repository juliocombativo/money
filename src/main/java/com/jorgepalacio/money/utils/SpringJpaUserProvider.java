package com.jorgepalacio.money.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.security.GrantedAuthority;
import org.springframework.security.GrantedAuthorityImpl;
import org.springframework.security.userdetails.UserDetails;
import org.springframework.security.userdetails.UserDetailsService;
import org.springframework.security.userdetails.UsernameNotFoundException;

import com.jorgepalacio.money.model.User;
import com.jorgepalacio.money.service.UserService;

public class SpringJpaUserProvider implements UserDetailsService {
	private UserService service;
	
	@Autowired
	public void setService(UserService service) {
		this.service = service;
	}
	
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException, DataAccessException {
		User user = service.byLogin(username);
		
		if(user == null)
			throw new UsernameNotFoundException(String.format("User '%s' was not found!", username));
		
		GrantedAuthorityImpl authority = new GrantedAuthorityImpl("ROLE_USER");
		return new org.springframework.security.userdetails.User(user.getLogin(), user.getPassword(), true, true, true, true, 
			new GrantedAuthority[] {authority});
	}
}
