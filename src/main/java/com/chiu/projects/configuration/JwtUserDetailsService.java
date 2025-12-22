package com.chiu.projects.configuration;

import java.util.ArrayList;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class JwtUserDetailsService implements UserDetailsService{
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		if("randomuser123".equals(username)) {
			return new User(username,
					"$2a$10$Zit9BRvxWAR/wMJQh37Lw.vZaxAQpOTZWScYOu5fnKENQmeAJgMG.",
					new ArrayList<>());
		}
		else {
			throw new UsernameNotFoundException("User not found with username " + username);
		}
	}
}